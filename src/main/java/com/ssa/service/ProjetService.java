package com.ssa.service;

import com.ssa.entity.Projet;

import java.util.List;

public interface ProjetService {
    public void save(Projet p);
    public Projet findById(String id);
    public List<Projet> findAll();
}
