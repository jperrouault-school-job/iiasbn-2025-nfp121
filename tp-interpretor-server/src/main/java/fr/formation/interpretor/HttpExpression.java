package fr.formation.interpretor;

import fr.formation.HttpJsonClient;

public abstract class HttpExpression implements Expression<String> {
    protected final HttpJsonClient httpJsonClient = new HttpJsonClient();
}
