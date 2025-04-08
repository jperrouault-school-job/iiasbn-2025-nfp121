package fr.formation.interpretor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HttpGetExpression extends HttpExpression {
    @Override
    public String interpret(InterpretorContext context) {
        UrlExpression urlExpression = new UrlExpression();
        
        context.setObject(this.httpJsonClient.get(urlExpression.interpret(context), context.getType()));

        return null;
    }
}
