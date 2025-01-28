package fr.formation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.model.Voiture;

public class Application {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        List<Voiture> voitures = new ArrayList<>();

        voitures.add(Voiture.builder().modele("Renault").build());
        voitures.add(Voiture.builder().modele("Citroën").build());
        voitures.add(Voiture.builder().modele("Peugeot").build());
        
        try (FileOutputStream fos = new FileOutputStream("voitures.json")) {
            byte[] json = mapper.writeValueAsBytes(voitures);
            fos.write(json);
        }

        catch (JsonProcessingException e) {
            System.out.println("Impossible de sérialiser !");
        }

        catch (IOException e) {
            System.out.println("Une erreur pendant l'écriture du flux JSON.");
        }
    }
}