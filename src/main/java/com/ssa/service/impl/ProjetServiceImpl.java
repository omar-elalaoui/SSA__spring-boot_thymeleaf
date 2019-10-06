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

        Phase2 phase2= new Phase2();
        Document releve_existant= documentService.convertFileToDocument(files.getreleve_existant());
        Document photo_site= documentService.convertFileToDocument(files.getphoto_site());
        Document esquisse= documentService.convertFileToDocument(files.getesquisse());
        Document plan_dwg= documentService.convertFileToDocument(files.getplan_dwg());

        return null;
    }
    
    @Override
    public Phase3 convertToPhase3(Phase3MultipartFiles files) {

        Phase3 phase3 = new Phase3();
        Document autorization= documentService.convertFileToDocument(files.getautorization());
        Document pv_commition= documentService.convertFileToDocument(files.getpv_commition());
        Document plan_approuve= documentService.convertFileToDocument(files.getplan_approuve());
        Document attestation_impl= documentService.convertFileToDocument(files.getattestation_impl());
        Document plan_beton_arme= documentService.convertFileToDocument(files.getplan_beton_arme());
        Document pv_suivi= documentService.convertFileToDocument(files.getpv_suivi());
        Document photo_exec= documentService.convertFileToDocument(files.getphoto_exec());

        return null;
    }
    
    @Override
    public Phase4 convertToPhase4(Phase4MultipartFiles files) {

        Phase4 phase4= new Phase4();
        Document photo_acheve= documentService.convertFileToDocument(files.getphoto_acheve());
        Document fermeture_chantier= documentService.convertFileToDocument(files.getfermeture_chantier());
        Document attestation_fin_travaux= documentService.convertFileToDocument(files.getattestation_fin_travaux());

        return null;
    }
}
