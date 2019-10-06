package com.ssa.service.impl;

import com.ssa.dao.ProjetRepository;
import com.ssa.entity.*;
import com.ssa.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Phase1 convertToPhase1(Phase1MultipartFiles files) throws IOException {
        Phase1MultipartFiles phase1MultipartFiles= files;
        Phase1 phase1= new Phase1();
        phase1.setCin_passeport(documentService.convertFileToDocument(files.getCin_passeport()));
        phase1.setContrat_arch(documentService.convertFileToDocument(files.getContrat_arch()));
        phase1.setDemande(documentService.convertFileToDocument(files.getDemande()));
        phase1.setDeclaration(documentService.convertFileToDocument(files.getDeclaration()));
        phase1.setCertificat_prop(documentService.convertFileToDocument(files.getCertificat_prop()));
        phase1.setPlan(documentService.convertFileToDocument(files.getPlan()));
        phase1.setListe_coord(documentService.convertFileToDocument(files.getListe_coord()));
        phase1.setPlan_cote(documentService.convertFileToDocument(files.getPlan_cote()));
        phase1.setNote_reinseignement(documentService.convertFileToDocument(files.getNote_reinseignement()));
        
        return phase1;
    }
    
    @Override
    public Phase2 convertToPhase2(Phase2MultipartFiles files) throws IOException {

        Phase2 phase2= new Phase2();
        phase2.setReleve_existant(documentService.convertFileToDocument(files.getReleve_existant()));
        phase2.setPhoto_site(documentService.convertFileToDocument(files.getPhoto_site()));
        phase2.setEsquisse(documentService.convertFileToDocument(files.getEsquisse()));
        phase2.setPlan_dwg(documentService.convertFileToDocument(files.getPlan_dwg()));

        return phase2;
    }
    
    @Override
    public Phase3 convertToPhase3(Phase3MultipartFiles files) throws IOException {

        Phase3 phase3 = new Phase3();
        phase3.setAutorization(documentService.convertFileToDocument(files.getAutorization()));
        phase3.setPv_commition(documentService.convertFileToDocument(files.getPv_commition()));
        phase3.setPlan_approuve(documentService.convertFileToDocument(files.getPlan_approuve()));
        phase3.setAttestation_impl(documentService.convertFileToDocument(files.getAttestation_impl()));
        phase3.setPlan_beton_arme(documentService.convertFileToDocument(files.getPlan_beton_arme()));
        phase3.setPv_suivi(documentService.convertFileToDocument(files.getPv_suivi()));
        phase3.setPhoto_exec(documentService.convertFileToDocument(files.getPhoto_exec()));

        return phase3;
    }
    
    @Override
    public Phase4 convertToPhase4(Phase4MultipartFiles files) throws IOException {

        Phase4 phase4= new Phase4();
        phase4.setPhoto_acheve(documentService.convertFileToDocument(files.getPhoto_acheve()));
        phase4.setFermeture_chantier(documentService.convertFileToDocument(files.getFermeture_chantier()));
        phase4.setAttestation_fin_travaux(documentService.convertFileToDocument(files.getAttestation_fin_travaux()));

        return phase4;
    }
}
