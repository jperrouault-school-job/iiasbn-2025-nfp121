package fr.formation.visitor;

public interface Visitable {
    public String accept(Visitor visitor);
}
