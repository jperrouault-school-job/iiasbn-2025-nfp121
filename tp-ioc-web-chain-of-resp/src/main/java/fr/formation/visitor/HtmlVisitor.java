package fr.formation.visitor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import fr.formation.annotation.Component;
import fr.formation.visitor.annotation.Html;

@Component
public class HtmlVisitor implements Visitor {
    public String visit(Visitable visitable) {
        StringBuilder sb = new StringBuilder();

        sb.append("<ul>");
        for (Map.Entry<String, String> propValue : this.findPropsValues(visitable).entrySet()) {
            sb.append("<li>");
            sb.append(propValue.getKey());
            sb.append(" -> ");
            sb.append(propValue.getValue());
            sb.append("</li>");
        }
        sb.append("</ul>");

        return sb.toString();
    }

    private Map<String, String> findPropsValues(Object visitable) {
        Map<String, String> propsValues = new HashMap<>();

        for (Field f : visitable.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Html.class)) {
                try {
                    f.setAccessible(true);
                    propsValues.put(f.getName(), f.get(visitable).toString());
                }
                
                catch (Exception e) {
                    propsValues.put(f.getName(), "erreur");
                }
            }
        }

        return propsValues;
    }
}
