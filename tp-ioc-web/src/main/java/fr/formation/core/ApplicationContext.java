package fr.formation.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import fr.formation.annotation.Bean;
import fr.formation.annotation.Component;
import fr.formation.annotation.Configuration;
import fr.formation.annotation.Controller;
import fr.formation.annotation.Inject;
import fr.formation.annotation.Scheduled;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApplicationContext {
    protected Map<Class<?>, Object> instances = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);

    public ApplicationContext(String packageName) {
        // Auto-référence
        this.instances.put(this.getClass(), this);

        Set<Class<?>> classes = this.findAllClassesByPackage(packageName);

        // Auto loading des classes
        for (Class<?> clz : classes) {
            // On doit alors l'instancier automatiquement
            if (clz.isAnnotationPresent(Component.class) || clz.isAnnotationPresent(Controller.class)) {
                log.debug("Création de {} ...", clz.getName());

                // On demander à créer l'instance, puis on l'ajoute au gestionnaire d'instances
                this.addBean(clz, BeanFactory.createBean(clz));
            }

            else if (clz.isAnnotationPresent(Configuration.class)) {
                log.debug("Création de la configuration {} ...", clz.getName());

                // On demander à créer l'instance, puis on l'ajoute au gestionnaire d'instances
                Object config = this.addBean(clz, BeanFactory.createBean(clz));

                // Parcours des méthodes à la recherche de @Bean
                for (Method m : clz.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(Bean.class)) {
                        log.debug("Exécution de la méthode {} ...", m.getName());
                        try {
                            this.addBean(m.getReturnType(), m.invoke(config));
                        }

                        catch (Exception e) {
                            log.error("Impossible d'exécuter la méthode : {}", e.getMessage());
                        }
                    }
                }
            }
        }

        // Injection des dépendances après toutes les constructions
        log.debug("Injection des dépendances ...");
        
        for (Map.Entry<Class<?>, Object> instanceEntry : this.instances.entrySet()) {
            this.inject(instanceEntry.getKey(), instanceEntry.getValue());
        }

        // Schedulers
        for (Class<?> clz : classes) {
            for (Method method : clz.getDeclaredMethods()) {
                Scheduled scheduled = method.getAnnotation(Scheduled.class);

                if (scheduled == null) {
                    continue;
                }

                Object instance = this.instances.get(clz);

                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        method.invoke(instance);
                    }
    
                    catch (Exception e) {
                        System.out.println("Ooops");
                    }
                }, 0, scheduled.delay(), TimeUnit.MILLISECONDS)
                ;

                // Thread t1 = new Thread(() -> {
                //     try {
                //         while (true) {
                //             method.invoke(instance);
                //             Thread.sleep(scheduled.delay());
                //         }
                //     }
    
                //     catch (Exception e) {
                //         System.out.println("Ooops");
                //     }
                // });

                // t1.start();
                // t1.interrupt();
            }
        }
    }

    public <T> T getBean(Class<T> clz) {
        return (T)this.instances.get(clz);
    }

    public Object addBean(Class<?> clz, Object instance) {
        this.instances.put(clz, instance);

        return instance;
    }

    private Set<Class<?>> findAllClassesByPackage(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Set<Class<?>> classes = new HashSet<>();

        log.debug("Scan du package {} ...", packageName);

        reader.lines().forEach(line -> {
            if (!line.endsWith(".class")) {
                classes.addAll(this.findAllClassesByPackage(packageName + "." + line));
            }

            else {
                Class<?> clz = this.findClassByNameAndPackage(line, packageName);

                if (clz != null) {
                    classes.add(clz);
                }
            }
        });

        log.debug("{} classes ont été trouvées!", classes.size());

        return classes;
    }
 
    private Class<?> findClassByNameAndPackage(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        }
        
        catch (ClassNotFoundException e) {
            return null;
        }
    }

    private void inject(Class<?> clz, Object instance) {
        for (Field field : clz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    Object dependency = this.getBean(field.getType());

                    // On injecte la dépendance si elle existe
                    if (dependency != null) {
                        log.debug("Injection de la dépendance {} pour {} ...", dependency.getClass().getName(), instance.getClass().getName());

                        field.setAccessible(true);
                        field.set(instance, dependency);
                        continue;
                    }

                    log.error("Pas de dépendance à injecter pour {} !", instance.getClass().getName());
                }

                catch (Exception ex) {
                    log.error("Impossible d'injecter la dépendance : {}", ex.getMessage());
                }
            }
        }
    }
}
