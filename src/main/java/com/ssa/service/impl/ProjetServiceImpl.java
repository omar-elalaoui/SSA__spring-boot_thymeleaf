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
    public void deleteById(String id) {
        projetRepository.deleteById(id);
    }
    
    @Override
    public List<Projet> findAll() {
        return projetRepository.findAll();
    }
    
    @Override
    public Phase1 convertToPhase1(Phase1MultipartFiles files, Phase1 phase1) throws IOException {
        Phase1 phase11= phase1;
        if(!files.getCin_passeport().isEmpty()){
            if(phase1.getCin_passeport() != null){
                Document document=documentService.convertFileToDocument(files.getCin_passeport());
                document.setId(phase1.getCin_passeport().getId());
                phase1.setCin_passeport(document);
            }else{
                phase1.setCin_passeport(documentService.convertFileToDocument(files.getCin_passeport()));
            }
        }
        if(!files.getContrat_arch().isEmpty()){
            if(phase1.getContrat_arch() != null){
                Document document=documentService.convertFileToDocument(files.getContrat_arch());
                document.setId(phase1.getContrat_arch().getId());
                phase1.setContrat_arch(document);
            }else {
                phase1.setContrat_arch(documentService.convertFileToDocument(files.getContrat_arch()));
            }
        }
        if(!files.getDemande().isEmpty()){
            if(phase1.getDemande() != null){
                Document document=documentService.convertFileToDocument(files.getDemande());
                document.setId(phase1.getDemande().getId());
                phase1.setDemande(document);
            }else {
                phase1.setDemande(documentService.convertFileToDocument(files.getDemande()));
            }
        }
        if(!files.getDeclaration().isEmpty()){
            if(phase1.getDeclaration() != null){
                Document document=documentService.convertFileToDocument(files.getDeclaration());
                document.setId(phase1.getDeclaration().getId());
                phase1.setDeclaration(document);
            }else {
                phase1.setDeclaration(documentService.convertFileToDocument(files.getDeclaration()));
            }
        }
        if(!files.getCertificat_prop().isEmpty()){
            if(phase1.getCertificat_prop() != null){
                Document document=documentService.convertFileToDocument(files.getCertificat_prop());
                document.setId(phase1.getCertificat_prop().getId());
                phase1.setCertificat_prop(document);
            }else {
                phase1.setCertificat_prop(documentService.convertFileToDocument(files.getCertificat_prop()));
            }
        }
        if(!files.getPlan().isEmpty()){
            if(phase1.getPlan() != null){
                Document document=documentService.convertFileToDocument(files.getPlan());
                document.setId(phase1.getPlan().getId());
                phase1.setPlan(document);
            }else {
                phase1.setPlan(documentService.convertFileToDocument(files.getPlan()));
            }
        }
        if(!files.getListe_coord().isEmpty()){
            if(phase1.getListe_coord() != null){
                Document document=documentService.convertFileToDocument(files.getListe_coord());
                document.setId(phase1.getListe_coord().getId());
                phase1.setListe_coord(document);
            }else {
                phase1.setListe_coord(documentService.convertFileToDocument(files.getListe_coord()));
            }
        }
        if(!files.getPlan_cote().isEmpty()){
            if(phase1.getPlan_cote() != null){
                Document document=documentService.convertFileToDocument(files.getPlan_cote());
                document.setId(phase1.getPlan_cote().getId());
                phase1.setPlan_cote(document);
            }else {
                phase1.setPlan_cote(documentService.convertFileToDocument(files.getPlan_cote()));
            }
        }
        if(!files.getNote_reinseignement().isEmpty()){
            if(phase1.getNote_reinseignement() != null){
                Document document=documentService.convertFileToDocument(files.getNote_reinseignement());
                document.setId(phase1.getNote_reinseignement().getId());
                phase1.setNote_reinseignement(document);
            }else {
                phase1.setNote_reinseignement(documentService.convertFileToDocument(files.getNote_reinseignement()));
            }
        }
        return phase1;
    }
    
    @Override
    public Phase2 convertToPhase2(Phase2MultipartFiles files, Phase2 phase2) throws IOException {
    
        if(!files.getReleve_existant().isEmpty()){
                if(phase2.getReleve_existant() != null){
                    Document document=documentService.convertFileToDocument(files.getReleve_existant());
                    document.setId(phase2.getReleve_existant().getId());
                    phase2.setReleve_existant(document);
                }else {
                    phase2.setReleve_existant(documentService.convertFileToDocument(files.getReleve_existant()));
                }
        }
        if(!files.getPhoto_site().isEmpty()){
                if(phase2.getPhoto_site() != null){
                    Document document=documentService.convertFileToDocument(files.getPhoto_site());
                    document.setId(phase2.getPhoto_site().getId());
                    phase2.setPhoto_site(document);
                }else {
                    phase2.setPhoto_site(documentService.convertFileToDocument(files.getPhoto_site()));
                }
        }
        if(!files.getEsquisse().isEmpty()){
                if(phase2.getEsquisse() != null){
                    Document document=documentService.convertFileToDocument(files.getEsquisse());
                    document.setId(phase2.getEsquisse().getId());
                    phase2.setEsquisse(document);
                }else {
                    phase2.setEsquisse(documentService.convertFileToDocument(files.getEsquisse()));
                }
        }
        if(!files.getPlan_dwg().isEmpty()){
                if(phase2.getPlan_dwg() != null){
                    Document document=documentService.convertFileToDocument(files.getPlan_dwg());
                    document.setId(phase2.getPlan_dwg().getId());
                    phase2.setPlan_dwg(document);
                }else {
                    phase2.setPlan_dwg(documentService.convertFileToDocument(files.getPlan_dwg()));
                }
        }

        return phase2;
    }
    
    @Override
    public Phase3 convertToPhase3(Phase3MultipartFiles files, Phase3 phase3) throws IOException {
        if(!files.getAutorization().isEmpty()){
                if(phase3.getAutorization() != null){
                    Document document=documentService.convertFileToDocument(files.getAutorization());
                    document.setId(phase3.getAutorization().getId());
                    phase3.setAutorization(document);
                }else {
                    phase3.setAutorization(documentService.convertFileToDocument(files.getAutorization()));
                }
        }
        if(!files.getPv_commition().isEmpty()){
                if(phase3.getPv_commition() != null){
                    Document document=documentService.convertFileToDocument(files.getPv_commition());
                    document.setId(phase3.getPv_commition().getId());
                    phase3.setPv_commition(document);
                }else {
                    phase3.setPv_commition(documentService.convertFileToDocument(files.getPv_commition()));
                }
            }
        if(!files.getPlan_approuve().isEmpty()){
                if(phase3.getPlan_approuve() != null){
                    Document document=documentService.convertFileToDocument(files.getPlan_approuve());
                    document.setId(phase3.getPlan_approuve().getId());
                    phase3.setPlan_approuve(document);
                }else {
                    phase3.setPlan_approuve(documentService.convertFileToDocument(files.getPlan_approuve()));
                }
            }
        if(!files.getAttestation_impl().isEmpty()){
                if(phase3.getAttestation_impl() != null){
                    Document document=documentService.convertFileToDocument(files.getAttestation_impl());
                    document.setId(phase3.getAttestation_impl().getId());
                    phase3.setAttestation_impl(document);
                }else {
                    phase3.setAttestation_impl(documentService.convertFileToDocument(files.getAttestation_impl()));
                }
            }
        if(!files.getPlan_beton_arme().isEmpty()){
                if(phase3.getPlan_beton_arme() != null){
                    Document document=documentService.convertFileToDocument(files.getPlan_beton_arme());
                    document.setId(phase3.getPlan_beton_arme().getId());
                    phase3.setPlan_beton_arme(document);
                }else {
                    phase3.setPlan_beton_arme(documentService.convertFileToDocument(files.getPlan_beton_arme()));
                }
            }
        if(!files.getPv_suivi().isEmpty()){
                if(phase3.getPv_suivi() != null){
                    Document document=documentService.convertFileToDocument(files.getPv_suivi());
                    document.setId(phase3.getPv_suivi().getId());
                    phase3.setPv_suivi(document);
                }else {
                    phase3.setPv_suivi(documentService.convertFileToDocument(files.getPv_suivi()));
                }
            }
        if(!files.getPhoto_exec().isEmpty()){
                if(phase3.getPhoto_exec() != null){
                    Document document=documentService.convertFileToDocument(files.getPhoto_exec());
                    document.setId(phase3.getPhoto_exec().getId());
                    phase3.setPhoto_exec(document);
                }else {
                    phase3.setPhoto_exec(documentService.convertFileToDocument(files.getPhoto_exec()));
                }
            }

        return phase3;
    }
    
    @Override
    public Phase4 convertToPhase4(Phase4MultipartFiles files, Phase4 phase4) throws IOException {

        if(!files.getPhoto_acheve().isEmpty()){
            if(phase4.getPhoto_acheve() != null){
                Document document=documentService.convertFileToDocument(files.getPhoto_acheve());
                document.setId(phase4.getPhoto_acheve().getId());
                phase4.setPhoto_acheve(document);
            }else {
                phase4.setPhoto_acheve(documentService.convertFileToDocument(files.getPhoto_acheve()));
            }
        }
        if(!files.getFermeture_chantier().isEmpty()){
            if(phase4.getFermeture_chantier() != null){
                Document document=documentService.convertFileToDocument(files.getFermeture_chantier());
                document.setId(phase4.getFermeture_chantier().getId());
                phase4.setFermeture_chantier(document);
            }else {
                phase4.setFermeture_chantier(documentService.convertFileToDocument(files.getFermeture_chantier()));
            }
        }
        if(!files.getAttestation_fin_travaux().isEmpty()){
            if(phase4.getAttestation_fin_travaux() != null){
                Document document=documentService.convertFileToDocument(files.getAttestation_fin_travaux());
                document.setId(phase4.getAttestation_fin_travaux().getId());
                phase4.setAttestation_fin_travaux(document);
            }else {
                phase4.setAttestation_fin_travaux(documentService.convertFileToDocument(files.getAttestation_fin_travaux()));
            }
        }

        return phase4;
    }
    
    
}
