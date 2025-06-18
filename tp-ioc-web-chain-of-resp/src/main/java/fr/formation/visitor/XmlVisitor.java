package fr.formation.visitor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import fr.formation.annotation.Component;
import fr.formation.visitor.annotation.Xml;

@Component
public class XmlVisitor implements Visitor {
    public String visit(Visitable visitable) {
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(visitable.getClass().getSimpleName().toLowerCase()).append(">");
        for (Map.Entry<String, String> propValue : this.findPropsValues(visitable).entrySet()) {
            sb.append("<").append(propValue.getKey()).append(">");
            sb.append(propValue.getValue());
            sb.append("</").append(propValue.getKey()).append(">");
        }
        sb.append("</").append(visitable.getClass().getSimpleName().toLowerCase()).append(">");

        return sb.toString();
    }

    private Map<String, String> findPropsValues(Object visitable) {
        Map<String, String> propsValues = new HashMap<>();

        for (Field f : visitable.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Xml.class)) {
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
