package com.ssa.service.impl;

import com.ssa.dao.ProjetRepository;
import com.ssa.entity.*;
import com.ssa.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProjetServiceImpl implements ProjetService {
    
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    DocumentServiceImpl documentService;
    
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
    
    @Override
    public Phase1 convertToPhase1(Phase1MultipartFiles files) {
        Phase1 phase1= new Phase1();
        Document cin_passeport= documentService.convertFileToDocument(files.getCin_passeport());
        Document contrat_arch= documentService.convertFileToDocument(files.getContrat_arch());
        Document demande= documentService.convertFileToDocument(files.getDemande());
        Document declaration= documentService.convertFileToDocument(files.getDeclaration());
        Document certificat_prop= documentService.convertFileToDocument(files.getCertificat_prop());
        Document plan= documentService.convertFileToDocument(files.getPlan());
        Document liste_coord= documentService.convertFileToDocument(files.getListe_coord());
        Document plan_cote= documentService.convertFileToDocument(files.getPlan_cote());
        Document note_reinseignement= documentService.convertFileToDocument(files.getNote_reinseignement());
        return null;
    }
    
    @Override
    public Phase2 convertToPhase2(Phase2MultipartFiles files) {
        return null;
    }
    
    @Override
    public Phase3 convertToPhase3(Phase3MultipartFiles files) {
        return null;
    }
    
    @Override
    public Phase4 convertToPhase4(Phase4MultipartFiles files) {
        return null;
    }
}
