package com.ssa.service;

import com.ssa.entity.Projet;
import java.util.List;

public interface ProjetService {
    public void save(Projet projet);
    public Projet findById(String id);
    public List<Projet> findAll();
}
