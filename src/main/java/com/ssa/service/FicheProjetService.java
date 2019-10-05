package com.ssa.service;

import java.util.List;

public interface FicheProjetService {
    public void save(FicheProjet p);
    public FicheProjet findById(int id);
    public List<FicheProjet> findAll();
}
