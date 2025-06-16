package fr.formation.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import fr.formation.visitor.Visitable;
import fr.formation.visitor.Visitor;
import fr.formation.visitor.annotation.Html;
import fr.formation.visitor.annotation.Xml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Produit implements Visitable {
    @Xml
    @Html
    private int id;
    
    @Html
    @JsonProperty
    private String nom;
    
    @JsonProperty
    @Xml
    @Html
    private BigDecimal prix;

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
