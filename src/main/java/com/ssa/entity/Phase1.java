package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase1 {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Document cin_passeport;
    @OneToOne(cascade = CascadeType.ALL)
    private Document contrat_arch;
    @OneToOne(cascade = CascadeType.ALL)
    private Document demande;
    @OneToOne(cascade = CascadeType.ALL)
    private Document declaration;
    @OneToOne(cascade = CascadeType.ALL)
    private Document certificat_prop;
    @OneToOne(cascade = CascadeType.ALL)
    private Document plan;
    @OneToOne(cascade = CascadeType.ALL)
    private Document liste_coord;
    @OneToOne(cascade = CascadeType.ALL)
    private Document plan_cote;
    @OneToOne(cascade = CascadeType.ALL)
    private Document note_reinseignement;
    
}
