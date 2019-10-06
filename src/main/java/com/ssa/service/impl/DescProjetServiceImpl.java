package com.ssa.service.impl;

import com.ssa.dao.DescProjetRepository;
import com.ssa.entity.DescProjet;
import com.ssa.service.DescProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescProjetServiceImpl implements DescProjetService {
    
    @Autowired
    DescProjetRepository descProjetRepository;
    
    @Override
    public void save(DescProjet descProjet) {
        descProjetRepository.save(descProjet);
    }
    
    @Override
    public DescProjet findById(long id) {
        return descProjetRepository.findById(id).get();
    }
    
}
