package fr.formation.core;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BeanFactory {
    private BeanFactory() { }

    public static <T> T createBean(Class<T> clz) {
        try {
            log.debug("Création d'une instance de {} ...", clz.getName());

            return clz.getConstructor().newInstance();
        }
        
        catch (Exception e) {
            log.error("Impossible de créer l'instance : {}", e.getMessage());
            return null;
        }
    }
}
