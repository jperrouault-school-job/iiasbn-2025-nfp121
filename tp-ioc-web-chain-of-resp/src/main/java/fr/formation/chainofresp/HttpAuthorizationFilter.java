package fr.formation.chainofresp;

import java.util.Base64;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;
import fr.formation.annotation.PreAuthorize;
import fr.formation.core.WebApplicationContext;
import fr.formation.core.WebApplicationContext.WebMethod;
import fr.formation.http.HttpRequest;
import fr.formation.http.HttpResponse;
import fr.formation.http.HttpResponseStatus;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class HttpAuthorizationFilter extends AbstractFilter {
    @Inject
    private WebApplicationContext ctx;

    @Override
    public void doChain(HttpRequest request, HttpResponse response) {
        WebMethod webMethod = this.ctx.getMethods().get(request.getPath());

        if (webMethod == null) {
            super.doChain(request, response);
            return;
        }

        PreAuthorize preAuthorize = webMethod.getMethod().getAnnotation(PreAuthorize.class);

        if (preAuthorize == null) {
            log.debug("Authorisation non demandée ...");
            super.doChain(request, response);
            return;
        }

        String authorizationHeader = request.getHeaders().get("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String[] usernameAndPassword = new String(Base64.getDecoder().decode(authorizationHeader.substring(6))).split(":");

            log.debug("Username = {}", usernameAndPassword[0]);
            
            if (preAuthorize.value().equals(usernameAndPassword[0])) {
                log.debug("Accès authorisé pour l'utilisation {} !", usernameAndPassword[0]);
                super.doChain(request, response);
                return;
            }
        }

        response.setStatus(HttpResponseStatus.FORBIDDEN);
        response.setContent("Accès refusé");
    }
}
