package fr.formation.service;

import java.util.List;

import fr.formation.annotation.Component;
import fr.formation.annotation.Inject;
import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;

@Component
public class ProduitService {
    @Inject
    private ProduitRepository repository;

    public List<Produit> findAll() {
        return this.repository.findAll();
    }
}
