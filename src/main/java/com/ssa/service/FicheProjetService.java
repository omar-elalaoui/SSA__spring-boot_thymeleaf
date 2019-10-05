package com.ssa.service;

import com.ssa.entity.FicheProjet;

import java.util.List;

public interface FicheProjetService {
    public void save(FicheProjet p);
    public FicheProjet findById(long id);
}
