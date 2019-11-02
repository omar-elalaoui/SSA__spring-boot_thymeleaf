package com.ssa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @OneToOne(cascade = CascadeType.ALL)
    private PJointes pJointes;
    @OneToOne(cascade = CascadeType.ALL)
    private SuiviChantier suiviChantier;
    
}
