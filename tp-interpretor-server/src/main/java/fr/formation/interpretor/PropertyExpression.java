package fr.formation.interpretor;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.HttpJsonClient;
import fr.formation.ObjectMapperFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PropertyExpression implements Expression<Evaluation> {
    protected final HttpJsonClient httpJsonClient = new HttpJsonClient();
    protected final ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    @Override
    public Evaluation interpret(InterpretorContext context) {
        String property = context.getExpressions().isEmpty() ? null : context.getExpressions().remove(0);
        
        if (property == null) {
            try {
                return Evaluation.builder()
                    .type(0)
                    .value(mapper.writeValueAsBytes(context.getObject()))
                    .build()
                ;
            }

            catch (Exception ex) {
                log.error("Impossible de sérialiser l'objet ...");
                return Evaluation.builder().build();
            }
        }

        try {
            String fieldName = (property.equals("image")) ? "thumbnailUrl" : property;
            Field field = context.getType().getDeclaredField(fieldName);
            
            field.setAccessible(true);

            String result = field.get(context.getObject()).toString();

            if (property.equals("image")) {
                return Evaluation.builder()
                    .type(2)
                    .value(this.httpJsonClient.get(result))
                    .build()
                ;
            }
            
            return Evaluation.builder()
                .type(1)
                .value(result.getBytes())
                .build()
            ;
        }
        
        catch (Exception e) {
            log.error("Impossible de lire la propriété " + property);
            return Evaluation.builder().build();
        }
    }
}
