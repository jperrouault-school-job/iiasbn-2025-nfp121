package fr.formation.interpretor;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InterpretorContext {
    private final String expression;
    private List<String> expressions;

    private Class<?> type;
    private int id;
    private Object object;

    public InterpretorContext(String expression) {
        this.expression = expression;
        this.expressions = new ArrayList<>(List.of(expression.split(" ")));
    }
}
