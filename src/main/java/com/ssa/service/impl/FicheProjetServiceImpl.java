package com.ssa.service.impl;

import com.ssa.dao.FicheProjetRepository;
import com.ssa.entity.FicheProjet;
import com.ssa.service.FicheProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FicheProjetServiceImpl implements FicheProjetService {
    
    @Autowired
    FicheProjetRepository ficheProjetRepository;
    
    @Override
    public void save(FicheProjet ficheProjet) {
        ficheProjetRepository.save(ficheProjet);
    }
    
    @Override
    public FicheProjet findById(long id) {
        return ficheProjetRepository.findById(id).get();
    }
    
}
