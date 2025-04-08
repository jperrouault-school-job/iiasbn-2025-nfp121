package fr.formation.interpretor;

public interface Expression<T> {
    public T interpret(InterpretorContext context);
}
