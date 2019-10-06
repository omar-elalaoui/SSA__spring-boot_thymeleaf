package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase4 {
    @Id @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Document photo_acheve;
    @OneToOne(cascade = CascadeType.ALL)
    private Document fermeture_chantier;
    @OneToOne(cascade = CascadeType.ALL)
    private Document attestation_fin_travaux;
}
