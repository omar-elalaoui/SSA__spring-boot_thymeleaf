package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PJointes {
    
    @Id @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Phase1 phase1;
    @OneToOne(cascade = CascadeType.ALL)
    private Phase2 phase2;
    @OneToOne(cascade = CascadeType.ALL)
    private Phase3 phase3;
    @OneToOne(cascade = CascadeType.ALL)
    private Phase4 phase4;
}
