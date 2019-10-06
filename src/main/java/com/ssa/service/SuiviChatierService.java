package com.ssa.service;

import com.ssa.entity.SuiviChantier;

public interface SuiviChatierService {
    public void save(SuiviChantier suiviChantier);
    public SuiviChantier findById(long id);
}
