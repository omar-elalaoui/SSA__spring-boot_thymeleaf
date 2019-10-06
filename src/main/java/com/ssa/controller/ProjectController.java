package com.ssa.controller;

import com.ssa.dao.Phase1Repository;
import com.ssa.dao.Phase2Repository;
import com.ssa.dao.Phase3Repository;
import com.ssa.dao.Phase4Repository;
import com.ssa.entity.*;
import com.ssa.service.SsaUtil;
import com.ssa.service.impl.DescProjetServiceImpl;
import com.ssa.service.impl.FicheProjetServiceImpl;
import com.ssa.service.impl.ProjetServiceImpl;
import com.ssa.service.impl.SuiviChantierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    
    
    @GetMapping("/add")
    public String add(Model model) {
        Projet projet= new Projet();
        projet.setFicheProjet(new FicheProjet());
        projet.setDescProjet(new DescProjet());
        projet.setSuiviChantier(new SuiviChantier());
        model.addAttribute("projet", projet);
        return "project_add";
    }
    
    @PostMapping("/add")
    public String add(Projet projet) {
        String generatedRef= SsaUtil.generateRef();
        projet.setRef(generatedRef);
        projet.getFicheProjet().setRef(generatedRef);
        projetService.save(projet);
        return "redirect:/projets";
    }
    
    
//    --------Affichage----------- //
    @GetMapping("")
    public String projets(Model model) {
        model.addAttribute("projets", projetService.findAll());
        return "projets";
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
    public String project_pices(Model model, @PathVariable(value="id") String id) {
        Projet projet= projetService.findById(id);
        model.addAttribute("project_id", projet.getRef());
        return "project_pices_phase1";
    }
    //    --------fin Affichage----------- //
    
    
    //    --------Affichage Edit----------- //
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
    //    --------fin Affichage Edit----------- //
    
    
    
    //    --------Edit----------- //
    @PostMapping("/fiche/edit")
    public String project_fiche_edit(FicheProjet ficheProjet) {
        ficheProjetService.save(ficheProjet);
        return "redirect:/projets/"+ficheProjet.getRef()+"/fiche";
    }
    @PostMapping("/description/edit")
    public String project_description_edit(DescProjet descProjet, String projet_ref) {
        descProjetService.save(descProjet);
        return "redirect:/projets/"+projet_ref+"/description";
    }
    @PostMapping("/suivi_chantier/edit")
    public String project_suivi_chantier_edit(SuiviChantier suiviChantier, String projet_ref) {
        suiviChantierService.save(suiviChantier);
        return "redirect:/projets/"+projet_ref+"/suivi_chantier";
    }
    //    --------fin Edit----------- //
    
    
    //    --------Affichage Phases----------- //
    @GetMapping("/{id}/pices/phase1/edit")
    public String phase1(Model model, @PathVariable(value="id") String id) {
        model.addAttribute("project_id", id);
        model.addAttribute("phase1MultipartFiles", new Phase1MultipartFiles());
        return "project_pices_phase1_edit";
    }
    //    --------fin Affichage Phases----------- //
    
    
    //    --------Edit Phases----------- //
    @PostMapping("{id}/phase1/edit")
    public String phase1_edit(Phase1MultipartFiles files, @PathVariable(value="id") String id) throws IOException {
        Projet projet= projetService.findById(id);
        Phase1 phase1= projetService.convertToPhase1(files);
        if(projet.getPJointes() != null){
            phase1.setId(projet.getPJointes().getPhase1().getId());
            phase1Service.save(phase1);
        }else{
            PJointes pJointes= new PJointes();
            pJointes.setPhase1(phase1);
            projet.setPJointes(pJointes);
            projetService.save(projet);
        }
        return "redirect:/projets/"+id+"/fiche";
    }
    //    --------fin Edit----------- //
    
    
    
    
    
    
    
    //    --------Affichage Documents----------- //
    @RequestMapping(value = "/{id}/documents/", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPic(@PathVariable(value="id") String id) throws IOException {
        
        return null;
    }
    
    
    //    --------fin Affichage Phases----------- //
    
}
