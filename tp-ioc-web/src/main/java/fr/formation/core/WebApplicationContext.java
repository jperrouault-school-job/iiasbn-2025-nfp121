package fr.formation.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import fr.formation.annotation.Controller;
import fr.formation.annotation.GetMapping;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WebApplicationContext extends ApplicationContext {
    @Getter
    private Map<String, WebMethod> methods = new HashMap<>();

    public WebApplicationContext(String packageName) {
        super(packageName);
        
        for (Object instance : this.instances.values()) {
            Class<?> clz = instance.getClass();

            if (!clz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            for (Method m : clz.getDeclaredMethods()) {
                GetMapping mapping = m.getAnnotation(GetMapping.class);

                if (mapping == null) {
                    continue;
                }

                log.debug("Mapping {} pour la méthode {} de l'instance {} !", mapping.value(), m.getName(), instance.getClass().getSimpleName());
                
                methods.put(mapping.value(), WebMethod.builder()
                    .instance(instance)
                    .method(m)
                    .build()
                );
            }
        }
    }

    @Getter @Setter
    @Builder
    public static class WebMethod {
        private Object instance;
        private Method method;

        public Object invoke() {
            try {
                return this.method.invoke(this.instance);
            }
            
            catch (Exception e) {
                log.error("Impossible d'exécuter la méthode {} : {}", this.method.getName(), e.getMessage());

                return null;
            }
        }
    }
}
