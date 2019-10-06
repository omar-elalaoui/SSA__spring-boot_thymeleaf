package com.ssa.service;

import com.ssa.entity.DescProjet;

import java.util.List;

public interface DescProjetService {
    public void save(DescProjet descProjet);
    public DescProjet findById(long id);
}
