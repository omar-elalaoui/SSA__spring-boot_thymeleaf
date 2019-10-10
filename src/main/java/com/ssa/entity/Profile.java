package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id @GeneratedValue
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String bio;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes;
}
