package com.ssa.entity;

import javax.persistence.*;
import java.util.List;

public class Role {
    @Id
    private String role;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="role"),
            inverseJoinColumns = @JoinColumn(name="username")
    )
    private List<User> users;
}