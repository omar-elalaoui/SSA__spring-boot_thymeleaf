package com.ssa.service;



import com.ssa.entity.*;

import java.io.IOException;
import java.util.List;

public interface ProjetService {
    public void save(Projet projet);
    public Projet findById(String id);
    public List<Projet> findAll();
    public Phase1 convertToPhase1(Phase1MultipartFiles files) throws IOException;
    public Phase2 convertToPhase2(Phase2MultipartFiles files) throws IOException;
    public Phase3 convertToPhase3(Phase3MultipartFiles files) throws IOException;
    public Phase4 convertToPhase4(Phase4MultipartFiles files) throws IOException;
}
