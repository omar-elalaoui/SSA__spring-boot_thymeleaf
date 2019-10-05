package com.ssa.service;

import java.util.List;

public interface ProjetService {
    public void save(Projet p);
    public Projet findById(int id);
    public List<Projet> findAll();
}
