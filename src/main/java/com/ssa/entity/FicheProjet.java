package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FicheProjet {

        @Id
        @GeneratedValue
        private long id;
        private String ref;
        private String nom;
        private String prenom;
        private String cin;
        private String contact;
        private String situation;
        private String nature_projet ;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date date_entree;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date date_sortie;
        private String dessinateur;
        private String observations;
    }



