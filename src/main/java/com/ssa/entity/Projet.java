package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Projet {
    @Id
    private String ref;
    @OneToOne(cascade = CascadeType.ALL)
    private FicheProjet ficheProjet;
    @OneToOne(cascade = CascadeType.ALL)
    private DescProjet descProjet;
    @OneToOne
    private PJointes pJointes;
    @OneToOne(cascade = CascadeType.ALL)
    private SuiviChantier suiviChantier;
    
}
