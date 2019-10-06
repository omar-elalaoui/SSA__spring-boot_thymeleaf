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

public class DescProjet {
    @Id
    @GeneratedValue
    private long id;
    private float surface_terrain;
    private float surface_au_sol;
    private float surface_couverte;
    private float cos;
    private float cus;
    private float prixm2;
    private float estimation_travaux;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date delai;
    private float trav_honoraire;
    private float mont_honoraire;
}
