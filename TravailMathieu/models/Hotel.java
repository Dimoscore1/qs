package fr.aem.TravailMathieu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Hotel")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private int id;

    @Column(name="nom", nullable=false)
    private String nom;

    @Column(name="adresse", nullable=false)
    private String adresse;

    @Column(name="taille", nullable=false)
    private string taille;



    //@OneToMany
    //private List<Vol> lesVolsDepart;

    //@OneToMany
    //private List<Vol> lesVolsArrivee;
}