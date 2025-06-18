package fr.formation.chainofresp;

import java.util.List;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;
import fr.formation.core.WebApplicationContext;
import fr.formation.core.WebApplicationContext.WebMethod;
import fr.formation.http.HttpContentType;
import fr.formation.http.HttpRequest;
import fr.formation.http.HttpResponse;
import fr.formation.visitor.HtmlVisitor;
import fr.formation.visitor.JsonVisitor;
import fr.formation.visitor.Visitable;
import fr.formation.visitor.XmlVisitor;

@Component
public class HttpAcceptFilter extends AbstractFilter {
    @Inject
    private WebApplicationContext ctx;

    @Inject
    private JsonVisitor jsonVisitor;

    @Inject
    private XmlVisitor xmlVisitor;

    @Inject
    private HtmlVisitor htmlVisitor;
 
    @Override
    public void doChain(HttpRequest request, HttpResponse response) {
        WebMethod webMethod = this.ctx.getMethods().get(request.getPath());

        if (webMethod == null) {
            super.doChain(request, response);
            return;
        }

        String acceptHeader = request.getHeaders().get("Accept");
        StringBuilder sb = new StringBuilder();
        Object result = webMethod.invoke();
        final String accept = (acceptHeader == null || acceptHeader.isBlank()) ? "*/*" : acceptHeader;

        if (result instanceof List<?> resultList) {
            resultList.stream()
                .filter(e -> e instanceof Visitable)
                .map(e -> (Visitable)e)
                .forEach((v) -> {
                    if (accept.equals("*/*") || accept.equals("application/json")) {
                        sb.append(v.accept(this.jsonVisitor));
                        sb.append("\n\r");
                    }
                    
                    if (accept.equals("*/*") || accept.equals("application/xml")) {
                        sb.append(v.accept(this.xmlVisitor));
                        sb.append("\n\r");
                    }
                    
                    if (accept.equals("*/*") || accept.equals("text/html")) {
                        sb.append(v.accept(this.htmlVisitor));
                        sb.append("\n\r");
                    }
                })
            ;
        }

        if (accept.equals("*/*")) {
            response.setContentType(HttpContentType.TEXT_PLAIN);
        }

        else if (accept.equals("application/json")) {
            response.setContentType(HttpContentType.APPLICATION_JSON);
        }

        else if (accept.equals("application/xml")) {
            response.setContentType(HttpContentType.APPLICATION_XML);
        }

        else if (accept.equals("text/html")) {
            response.setContentType(HttpContentType.TEXT_HTML);
        }
        
        response.setContent(sb.toString());
    }
}
