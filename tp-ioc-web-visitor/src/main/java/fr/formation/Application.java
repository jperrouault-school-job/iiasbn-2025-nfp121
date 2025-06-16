package fr.formation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import fr.formation.core.ApplicationContext;
import fr.formation.core.WebApplicationContext;
import fr.formation.http.HttpServer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {
    public static void main(String[] args) {
        Configurator.setRootLevel(Level.DEBUG); // Pour les logs

        ApplicationContext context = new WebApplicationContext(Application.class.getPackageName());

        HttpServer server = context.getBean(HttpServer.class);

        new Thread(server::listen).start();
    }
}
