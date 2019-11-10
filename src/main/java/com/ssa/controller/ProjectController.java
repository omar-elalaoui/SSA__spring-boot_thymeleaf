package com.ssa.controller;

import com.ssa.dao.*;
import com.ssa.entity.*;
import com.ssa.service.SsaUtil;
import com.ssa.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/projets")
public class ProjectController {
    
    @Autowired
    ProjetServiceImpl projetService;
    @Autowired
    FicheProjetServiceImpl ficheProjetService;
    @Autowired
    DescProjetServiceImpl descProjetService;
    @Autowired
    SuiviChantierServiceImpl suiviChantierService;
    @Autowired
    Phase1Repository phase1Service;
    @Autowired
    Phase2Repository phase2Service;
    @Autowired
    Phase3Repository phase3Service;
    @Autowired
    Phase4Repository phase4Service;
    @Autowired
    LogServiceImpl logService;
    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    NotificationRepository notificationRepository;
    
    
    @PostMapping("/add")
    public String add(Projet projet) {
        String generatedRef= SsaUtil.generateRef();
        projet.setRef(generatedRef);
        projet.getFicheProjet().setRef(generatedRef);
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+")";
        logService.ajout_log(projectDes);
        projetService.save(projet);
        return "redirect:/projets";
    }
    
    @GetMapping("/{username}/delete")
    public String delete(@PathVariable(value="username") String username, Model model) {
        Notification notification= notificationRepository.findByProjetid(username);
        if(notification != null){ notificationService.deleteById(notification.getId());}
        Projet projet= projetService.findById(username);
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+")";
        logService.supp_log(projectDes);
        projetService.deleteById(username);
        return "redirect:/projets";
    }
    
    
//    --------Affichage----------- //
    @GetMapping("")
    public String projets(Model model) {
        List<Projet> projetList= projetService.findAll();
        Collections.reverse(projetList);
        model.addAttribute("projets", projetList);
        return "projets";
    }
    @PostMapping("/chercher_projet")
    public String chercher_projets(Model model, String str) {
        List<Projet> projetListCherchee= new ArrayList<Projet>();
        List<Projet> projetList= projetService.findAll();
        Collections.reverse(projetList);
        for(Projet projet: projetList){
            String prenom_nom= projet.getFicheProjet().getPrenom() +" "+ projet.getFicheProjet().getNom();
            if( Pattern.compile(Pattern.quote(str), Pattern.CASE_INSENSITIVE).matcher(prenom_nom).find()){projetListCherchee.add(projet);}
            else{
                String ref= projet.getRef();
                if(Pattern.compile(Pattern.quote(str), Pattern.CASE_INSENSITIVE).matcher(ref).find()) projetListCherchee.add(projet);
            }
        }
        model.addAttribute("projets", projetListCherchee);
        return "projets";
    }
    @GetMapping("/add")
    public String add(Model model) {
        Projet projet= new Projet();
        projet.setFicheProjet(new FicheProjet());
        projet.setDescProjet(new DescProjet());
        projet.setSuiviChantier(new SuiviChantier());
        model.addAttribute("projet", projet);
        return "project_add";
    }
    @GetMapping("/{id}/fiche")
    public String project_fiche(Model model, @PathVariable(value="id") String id) {
        model.addAttribute("fiche", projetService.findById(id).getFicheProjet());
        return "project_fiche";
    }
    @GetMapping("/{id}/description")
    public String project_desc(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        model.addAttribute("project_id", projet.getRef());
        model.addAttribute("description", projet.getDescProjet());
        return "project_desc";
    }
    @GetMapping("/{id}/suivi_chantier")
    public String project_suivi(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        model.addAttribute("project_id", projet.getRef());
        model.addAttribute("suivi_chantier", projet.getSuiviChantier());
        return "project_suivi_chantier";
    }
    @GetMapping("/{id}/pices")
    public String dd(@PathVariable(value="id") String id) {
        return "redirect:/projets/"+projetService.findById(id).getRef()+"/pices/phase1";
    }
    
    //    --------fin Affichage----------- //
    
    
    //    --------Affichage Project Edit----------- //
    @GetMapping("/{id}/fiche/form")
    public String project_fiche_form(Model model, @PathVariable(value="id") String id) {
        model.addAttribute("ficheProjet", projetService.findById(id).getFicheProjet());
        return "project_fiche_edit";
    }
    @GetMapping("/{id}/description/form")
    public String project_desc_form(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        model.addAttribute("project_id", projet.getRef());
        model.addAttribute("description", projet.getDescProjet());
        return "project_desc_edit";
    }
    @GetMapping("/{id}/suivi_chantier/form")
    public String project_suivi_form(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        model.addAttribute("project_id", projet.getRef());
        model.addAttribute("suivi_chantier", projet.getSuiviChantier());
        return "project_suivi_chantier_edit";
    }
    //    --------fin Affichage Project Edit----------- //
    
    
    
    //    --------Project Edit----------- //
    @PostMapping("/fiche/edit")
    public String project_fiche_edit(FicheProjet ficheProjet) {
        String projectDes= ficheProjet.getPrenom()+" "+ficheProjet.getNom()+" ("+ficheProjet.getRef()+") [Fiche Projet]";
        logService.modification_log(projectDes);
        ficheProjetService.save(ficheProjet);
        return "redirect:/projets/"+ficheProjet.getRef()+"/fiche";
    }
    @PostMapping("/description/edit")
    public String project_description_edit(DescProjet descProjet, String projet_ref) {
        Projet projet= projetService.findById(projet_ref);
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+") [Description]";
        logService.modification_log(projectDes);
        descProjetService.save(descProjet);
        return "redirect:/projets/"+projet_ref+"/description";
    }
    @PostMapping("/suivi_chantier/edit")
    public String project_suivi_chantier_edit(SuiviChantier suiviChantier, String projet_ref) {
        Projet projet= projetService.findById(projet_ref);
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+") [Suivi Chantier]";
        logService.modification_log(projectDes);
        suiviChantierService.save(suiviChantier);
        return "redirect:/projets/"+projet_ref+"/suivi_chantier";
    }
    //    --------fin Project Edit----------- //
    
    
    
    
    
    
    
    
    
    
    //    --------Affichage Phases----------- //
    @GetMapping("/{id}/pices/phase1")
    public String project_pices(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        model.addAttribute("project_id", projet.getRef());
        if(projet.getPJointes() != null){
            Phase1 phase1Temp= projet.getPJointes().getPhase1();
            model.addAttribute("phase1", phase1Temp);
            model.addAttribute("phase1_full", SsaUtil.isPhase1Full(phase1Temp));
        }else{
            model.addAttribute("phase1", new Phase1());
            model.addAttribute("phase1_full", false);
        }
        return "project_pices_phase1";
    }
    
    @GetMapping("/{id}/pices/phase2")
    public String phase214(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase2 phase2=new Phase2();
        boolean phase2_full=false;
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase2() != null){
                phase2= projet.getPJointes().getPhase2();
                phase2_full= SsaUtil.isPhase2Full(phase2);
            }
        }
        model.addAttribute("phase2_full", phase2_full);
        model.addAttribute("phase2", phase2);
        model.addAttribute("project_id", projetService.findById(id).getRef());
        return "project_pices_phase2";
    }
    
    @GetMapping("/{id}/pices/phase3")
    public String phase214a(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase3 phase3=new Phase3();
        boolean phase3_full=false;
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase3() != null){
                phase3= projet.getPJointes().getPhase3();
                phase3_full=SsaUtil.isPhase3Full(phase3);
            }
        }
        model.addAttribute("phase3_full", phase3_full);
        model.addAttribute("phase3", phase3);
        model.addAttribute("project_id", projetService.findById(id).getRef());
        return "project_pices_phase3";
    }
    
    @GetMapping("/{id}/pices/phase4")
    public String phase214z(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase4 phase4=new Phase4();
        Phase3 phase3=new Phase3();
        boolean phase4_full=false;
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase4() != null){
                phase4= projet.getPJointes().getPhase4();
                phase4_full= SsaUtil.isPhase4Full(phase4);
            }
        }
        model.addAttribute("phase4_full", phase4_full);
        model.addAttribute("phase4", phase4);
        model.addAttribute("project_id", projetService.findById(id).getRef());
        return "project_pices_phase4";
    }
    //    --------fin Affichage Phases----------- //
    
    
    //    --------Affichage Phases edit----------- //
    @GetMapping("/{id}/pices/phase1/edit")
    public String phase1(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase1 phase1=new Phase1();
        if(projet.getPJointes() != null){
            phase1= projet.getPJointes().getPhase1();
            model.addAttribute("phase1_empty", false);
        }else{
            model.addAttribute("phase1_empty", true);
        }
        model.addAttribute("phase1", phase1);
        model.addAttribute("project_id", id);
        model.addAttribute("phase1MultipartFiles", new Phase1MultipartFiles());
        return "project_pices_phase1_edit";
    }
    @GetMapping("/{id}/pices/phase2/edit")
    public String phase48(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase2 phase2=new Phase2();
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase2() != null){
                phase2= projet.getPJointes().getPhase2();
            }
        }
        model.addAttribute("phase2", phase2);
        model.addAttribute("project_id", id);
        model.addAttribute("phase2MultipartFiles", new Phase2MultipartFiles());
        return "project_pices_phase2_edit";
    }
    @GetMapping("/{id}/pices/phase3/edit")
    public String phase488(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase3 phase3=new Phase3();
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase3() != null){
                phase3= projet.getPJointes().getPhase3();
            }
        }
        model.addAttribute("phase3", phase3);
        model.addAttribute("project_id", id);
        model.addAttribute("phase3MultipartFiles", new Phase3MultipartFiles());
        return "project_pices_phase3_edit";
    }
    @GetMapping("/{id}/pices/phase4/edit")
    public String phase4888(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        Phase4 phase4=new Phase4();
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase4() != null){
                phase4= projet.getPJointes().getPhase4();
            }
        }
        model.addAttribute("phase4", phase4);
        model.addAttribute("project_id", id);
        model.addAttribute("phase4MultipartFiles", new Phase4MultipartFiles());
        return "project_pices_phase4_edit";
    }
    //    --------fin Affichage Phases edit----------- //
    
    
    
    //    --------Edit Phases----------- //
    @PostMapping("{id}/phase1/edit")
    public String phase1_edit(Phase1MultipartFiles files, @PathVariable(value="id") String id) throws IOException {
        Projet projet= projetService.findById(id);
        Phase1 phase1;
        if(projet.getPJointes() != null){
            phase1=projetService.convertToPhase1(files, projet.getPJointes().getPhase1());
            phase1.setId(projet.getPJointes().getPhase1().getId());
            phase1Service.save(phase1);
        }else{
            phase1=projetService.convertToPhase1(files, new Phase1());
            PJointes pJointes= new PJointes();
            pJointes.setPhase1(phase1);
            projet.setPJointes(pJointes);
            projetService.save(projet);
        }
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+") [Phase 1]";
        logService.modification_log(projectDes);
        return "redirect:/projets/"+id+"/pices/phase1";
    }
    
    @PostMapping("{id}/phase2/edit")
    public String phase2_edit(Phase2MultipartFiles files, @PathVariable(value="id") String id) throws IOException {
        Projet projet= projetService.findById(id);
        Phase2 phase2;
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase2() != null){
                phase2=projetService.convertToPhase2(files, projet.getPJointes().getPhase2());
                phase2.setId(projet.getPJointes().getPhase2().getId());
                phase2Service.save(phase2);
            }else {
                phase2=projetService.convertToPhase2(files, new Phase2());
                projet.getPJointes().setPhase2(phase2);
                projetService.save(projet);
            }
        }
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+") [Phase 2]";
        logService.modification_log(projectDes);
        return "redirect:/projets/"+id+"/pices/phase2";
    }
    
    @PostMapping("{id}/phase3/edit")
    public String phase3_edit(Phase3MultipartFiles files, @PathVariable(value="id") String id) throws IOException {
        Projet projet= projetService.findById(id);
        Phase3 phase3;
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase3() != null){
                phase3=projetService.convertToPhase3(files, projet.getPJointes().getPhase3());
                phase3.setId(projet.getPJointes().getPhase3().getId());
                phase3Service.save(phase3);
            }
            else {
                phase3=projetService.convertToPhase3(files, new Phase3());
                projet.getPJointes().setPhase3(phase3);
                projetService.save(projet);
            }
        }
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+") [Phase 3]";
        logService.modification_log(projectDes);
        return "redirect:/projets/"+id+"/pices/phase3";
    }
    
    @PostMapping("{id}/phase4/edit")
    public String phase4_edit(Phase4MultipartFiles files, @PathVariable(value="id") String id) throws IOException {
        Projet projet= projetService.findById(id);
        Phase4 phase4;
        if(projet.getPJointes() != null){
            if(projet.getPJointes().getPhase4() != null){
                phase4=projetService.convertToPhase4(files, projet.getPJointes().getPhase4());
                phase4.setId(projet.getPJointes().getPhase4().getId());
                phase4Service.save(phase4);
            }else {
                phase4=projetService.convertToPhase4(files, new Phase4());
                projet.getPJointes().setPhase4(phase4);
                projetService.save(projet);
            }
        }
        String projectDes= projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom()+" ("+projet.getRef()+") [Phase 4]";
        logService.modification_log(projectDes);
        return "redirect:/projets/"+id+"/pices/phase4";
    }
    //    --------fin Edit----------- //
    
    
    
    
    
    
    
    //    --------Affichage Documents----------- //
    
    
    
    //    --------Phase1----------- //
    @RequestMapping(value = "/{id}/documents/cin_passeport", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhase1Doc1(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getCin_passeport().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/contrat_arch", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] contrat_arch(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getContrat_arch().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/demande", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] demande(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getDemande().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/declaration", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] declaration(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getDeclaration().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/certificat_prop", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] certificat_prop(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getCertificat_prop().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/plan", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] plan(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getPlan().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/liste_coord", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] liste_coord(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getListe_coord().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/plan_cote", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] plan_cote(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getPlan_cote().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/note_reinseignement", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] note_reinseignement(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase1().getNote_reinseignement().getDocData();
    }
    //    --------fin Phase1----------- //
    
    
    //    --------Phase2----------- //
    @RequestMapping(value = "/{id}/documents/releve_existant", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] releve_existant(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase2().getReleve_existant().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/photo_site", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] photo_site(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase2().getPhoto_site().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/esquisse", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] esquisse(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase2().getEsquisse().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/plan_dwg", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] plan_dwg(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase2().getPlan_dwg().getDocData();
    }
    //    --------fin Phase2----------- //
    
    
    //    --------Phase3----------- //
    @RequestMapping(value = "/{id}/documents/autorization", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] autorization(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getAutorization().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/pv_commition", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pv_commition(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getPv_commition().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/plan_approuve", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] plan_approuve(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getPlan_approuve().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/attestation_impl", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] attestation_impl(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getAttestation_impl().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/plan_beton_arme", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] plan_beton_arme(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getPlan_beton_arme().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/pv_suivi", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pv_suivi(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getPv_suivi().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/photo_exec", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] photo_exec(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase3().getPhoto_exec().getDocData();
    }
    //    --------fin Phase3----------- //
    
    //    --------Phase4----------- //
    @RequestMapping(value = "/{id}/documents/photo_acheve", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] photo_acheve(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase4().getPhoto_acheve().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/fermeture_chantier", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] fermeture_chantier(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase4().getFermeture_chantier().getDocData();
    }
    @RequestMapping(value = "/{id}/documents/attestation_fin_travaux", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] attestation_fin_travaux(@PathVariable(value="id") String id) throws IOException {
        return projetService.findById(id).getPJointes().getPhase4().getAttestation_fin_travaux().getDocData();
    }
    //    --------fin Phase4----------- //
    
    
    //    --------fin Affichage Phases----------- //
    
}
