package fr.formation.interpretor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NumberExpression implements Expression<Integer> {
    @Override
    public Integer interpret(InterpretorContext context) {
        String number = context.getExpressions().remove(0);

        try {
            int value = Integer.parseInt(number);

            context.setId(value);

            log.debug("ID {} retrouvé !", value);

            return value;
        }
        
        catch (NumberFormatException nfe) {
            log.error("ID ne peut pas être retrouvé : {}", nfe.getMessage());
            return 0;
        }
    }
}
