package fr.formation.repo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.formation.annotation.Component;
import fr.formation.model.Produit;

@Component
public class ProduitRepository {
    public List<Produit> findAll() {
        List<Produit> produits = new ArrayList<>();

        produits.add(Produit.builder()
            .id(1)
            .nom("Parachute de France")
            .prix(new BigDecimal(9999)).build()
        );

        produits.add(Produit.builder()
            .id(2)
            .nom("Casque de parachutiste")
            .prix(new BigDecimal(449.5)).build()
        );
        
        produits.add(Produit.builder()
            .id(3)
            .nom("Combinaison de parachutiste")
            .prix(new BigDecimal(399)).build()
        );

        return produits;
    }
}
