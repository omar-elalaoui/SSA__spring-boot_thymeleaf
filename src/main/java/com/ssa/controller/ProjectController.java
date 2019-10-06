package com.ssa.controller;

import com.ssa.entity.*;
import com.ssa.service.SsaUtil;
import com.ssa.service.impl.DescProjetServiceImpl;
import com.ssa.service.impl.FicheProjetServiceImpl;
import com.ssa.service.impl.ProjetServiceImpl;
import com.ssa.service.impl.SuiviChantierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return "project_pices";
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
    
    
    //    --------Edit Phases----------- //
    @PostMapping("{id}/phase1/edit")
    public String phase1_edit(@RequestParam("files") MultipartFile[] files, @PathVariable(value="id") String id) {
  
        return "redirect:/projets/"+id+"/fiche";
    }
    //    --------fin Edit----------- //
    
    
    
}
