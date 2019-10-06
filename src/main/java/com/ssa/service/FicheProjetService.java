package com.ssa.service;

import com.ssa.entity.FicheProjet;

import java.util.List;

public interface FicheProjetService {
    public void save(FicheProjet ficheProjet);
    public FicheProjet findById(long id);
}
