package com.ssa.service.impl;

import com.ssa.dao.ProjetRepository;
import com.ssa.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjetServiceImpl implements ProjetService {
    
    @Autowired
    ProjetRepository projetRepository;
    @Override
    public void save(Projet projet) {
        projetRepository.save(projet);
    }
    
    @Override
    public Projet findById(String ref) {
        return projetRepository.findById(ref).get();
    }
    
    @Override
    public List<Projet> findAll() {
        return projetRepository.findAll();
    }
}
