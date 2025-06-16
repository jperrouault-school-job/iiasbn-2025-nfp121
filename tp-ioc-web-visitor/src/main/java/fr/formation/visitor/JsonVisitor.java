package fr.formation.visitor;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;

@Component
public class JsonVisitor implements Visitor {
    @Inject
    private ObjectMapper mapper;

    public String visit(Visitable visitable) {
        try {
            return this.mapper.writeValueAsString(visitable);
        }

        catch (Exception e) {
            return "error json";
        }
    }
}
