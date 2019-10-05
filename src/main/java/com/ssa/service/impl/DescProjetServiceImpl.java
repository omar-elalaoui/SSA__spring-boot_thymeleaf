package com.ssa.service.impl;

import com.ssa.dao.DescProjetRepository;
import com.ssa.entity.DescProjet;
import com.ssa.service.DescProjetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DescProjetServiceImpl implements DescProjetService {
    
    @Autowired
    DescProjetRepository descProjetRepository;
    
    @Override
    public void save(DescProjet p) {
    
    }
    
    @Override
    public DescProjet findById(long id) {
        return null;
    }
    
}
