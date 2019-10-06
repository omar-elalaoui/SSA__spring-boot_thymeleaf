package com.ssa.service.impl;

import com.ssa.dao.SuiviChantierRepository;
import com.ssa.entity.SuiviChantier;
import com.ssa.service.SuiviChatierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuiviChantierServiceImpl implements SuiviChatierService {
    @Autowired
    SuiviChantierRepository suiviChantierRepository;
    
    @Override
    public void save(SuiviChantier suiviChantier) {
        suiviChantierRepository.save(suiviChantier);
    }
    
    @Override
    public SuiviChantier findById(long id) {
        return suiviChantierRepository.findById(id).get();
    }
}
