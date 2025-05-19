package fr.formation.controller;

import java.util.List;

import fr.formation.annotation.Controller;
import fr.formation.annotation.GetMapping;
import fr.formation.annotation.Inject;
import fr.formation.model.Produit;
import fr.formation.service.ProduitService;

@Controller
public class ProduitController {
    @Inject
    private ProduitService service;

    @GetMapping("/produit")
    public List<Produit> findAll() {
        return this.service.findAll();
    }
}
