package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FicheProjet {

        @Id
        private long id;
        private String ref;
        private String nom;
        private String prenom;
        private String cin;
        private String contact;
        private String situation;
        private String nature_projet ;
        private Date date_entree;
        private Date date_sortie;
        private String dessinateur;
        private String observations;
    }



