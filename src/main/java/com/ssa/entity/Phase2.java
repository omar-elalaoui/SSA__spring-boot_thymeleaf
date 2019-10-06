package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase2 {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Document releve_existant;
    @OneToOne(cascade = CascadeType.ALL)
    private Document photo_site;
    @OneToOne(cascade = CascadeType.ALL)
    private Document esquisse;
    @OneToOne(cascade = CascadeType.ALL)
    private Document plan_dwg;
}
