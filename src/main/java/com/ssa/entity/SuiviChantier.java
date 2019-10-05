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

public class SuiviChantier {

    @Id
    private long id;
    private Date date_ouv_chantier;
     private Date date_ferm_chantier;
     private Date date_impl;
     private String nom_agent;
     private String observations;
}
