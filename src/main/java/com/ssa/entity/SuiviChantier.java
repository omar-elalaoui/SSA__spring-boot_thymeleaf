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

public class SuiviChantier {

    @Id
    @GeneratedValue
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_ouv_chantier;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date date_ferm_chantier;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date date_impl;
     private String nom_agent;
     private String observations;
}
