package fr.formation.interpretor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Interpretor {
    private Interpretor() { }
    
    public static Evaluation evaluate(String value) {
        InterpretorContext context = new InterpretorContext(value);
        HttpCommandExpression httpCommandExpression = new HttpCommandExpression();
        HttpExpression httpExpression = null;
        TypeExpression typeExpression = new TypeExpression();
        NumberExpression numberExpression = new NumberExpression();
        PropertyExpression propertyExpression = new PropertyExpression();
        
        log.debug("Evaluation de l'expression {} ...", value);
        
        try {
            httpExpression = httpCommandExpression.interpret(context);
            
            if (httpExpression == null) {
                log.error("L'expression ne peut pas être évaluée !");
                return Evaluation.builder().build();
            }
            
            typeExpression.interpret(context);
            numberExpression.interpret(context);
            httpExpression.interpret(context);
            
            return propertyExpression.interpret(context);
        }

        catch (Exception e) {
            log.error("L'expression ne peut pas être évaluée !");
            return Evaluation.builder().build();
        }
    }
}
