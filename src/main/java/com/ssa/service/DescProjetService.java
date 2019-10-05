package com.ssa.service;

import java.util.List;

public interface DescProjetService {
    public void save(DescProjet p);
    public DescProjet findById(int id);
    public List<DescProjet> findAll();
}
