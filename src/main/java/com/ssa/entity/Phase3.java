package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase3 {
    @Id  @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Document autorization;
    @OneToOne(cascade = CascadeType.ALL)
    private Document pv_commition;
    @OneToOne(cascade = CascadeType.ALL)
    private Document plan_approuve;
    @OneToOne(cascade = CascadeType.ALL)
    private Document attestation_impl;
    @OneToOne(cascade = CascadeType.ALL)
    private Document plan_beton_arme;
    @OneToOne(cascade = CascadeType.ALL)
    private Document pv_suivi;
    @OneToOne(cascade = CascadeType.ALL)
    private Document photo_exec;
}
