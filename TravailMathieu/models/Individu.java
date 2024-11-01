package fr.aem.TravailMathieu.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="individus")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Individu {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // l'id est un entier qui s'incrémente tout seul
    @Column(name="id", nullable=false)
    private int id;

    @Column(name="nom", nullable=false)
    private String nom;

    @Column(name="prenom", nullable=false)
    private String prenom;

    @Column(name="dateNaissance", nullable=false)
    private LocalDate dateNaissance;
}