package fr.formation.interpretor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpCommandExpression implements Expression<HttpExpression> {
    @Override
    public HttpExpression interpret(InterpretorContext context) {
        String command = context.getExpressions().remove(0);

        if ("get".equalsIgnoreCase(command)) {
            log.error("Commande HTTP GET retrouvée !");

            return new HttpGetExpression();
        }

        log.error("Aucune commande HTTP n'a été retrouvée !");

        return null;
    }
}
