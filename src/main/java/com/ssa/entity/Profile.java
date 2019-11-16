package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Profile {
    @Id @GeneratedValue
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String bio;
    @Lob
    private byte[] image;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private List<Note> notes;
}
