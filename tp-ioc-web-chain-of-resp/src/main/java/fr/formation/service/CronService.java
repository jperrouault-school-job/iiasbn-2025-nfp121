package fr.formation.service;

import fr.formation.annotation.Component;
import fr.formation.annotation.Scheduled;

@Component
public class CronService {
    // @Scheduled(delay = 2_000)
    public void exemple() {
        // ça s'affichera toutes les 2 secondes (2000ms)
        System.out.println("Tache planifiée");
    }
}
