package fr.formation.interpretor;

import fr.formation.interpretor.exception.InvalidTypeException;
import fr.formation.model.Album;
import fr.formation.model.Photo;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TypeExpression implements Expression<Class<?>> {
    @Override
    public Class<?> interpret(InterpretorContext context) {
        String type = context.getExpressions().remove(0);

        if ("photo".equalsIgnoreCase(type)) {
            context.setType(Photo.class);
        }

        else if ("album".equalsIgnoreCase(type)) {
            context.setType(Album.class);
        }

        else {
            log.error("Le type ne peut pas être retrouvé !");
            throw new InvalidTypeException();
        }

        log.debug("Type {} retrouvé !", type);

        return context.getType();
    }
}
