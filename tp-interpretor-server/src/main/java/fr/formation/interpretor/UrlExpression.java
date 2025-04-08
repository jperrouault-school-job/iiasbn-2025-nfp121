package fr.formation.interpretor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UrlExpression implements Expression<String> {
    @Override
    public String interpret(InterpretorContext context) {
        String type = context.getType().getSimpleName();
        int id = context.getId();

        if ("photo".equalsIgnoreCase(type)) {
            return "https://jsonplaceholder.typicode.com/photos/" + id;
        }

        if ("album".equalsIgnoreCase(type)) {
            return "https://jsonplaceholder.typicode.com/albums/" + id;
        }

        return null;
    }
}
