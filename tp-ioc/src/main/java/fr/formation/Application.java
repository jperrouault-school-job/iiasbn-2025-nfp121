package fr.formation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import fr.formation.core.ApplicationContext;
import fr.formation.service.PhotoService;
import fr.formation.service.ProduitService;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {
    public static void main(String[] args) {
        Configurator.setRootLevel(Level.DEBUG); // Pour les logs

        ApplicationContext context = new ApplicationContext(Application.class.getPackageName());
        ProduitService produitService = context.getBean(ProduitService.class);
        PhotoService photoService = context.getBean(PhotoService.class);

        System.out.println(produitService.findAll());
        System.out.println(photoService.findAll());
    }
}
