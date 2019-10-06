package com.ssa.controller;

import com.ssa.entity.DescProjet;
import com.ssa.entity.FicheProjet;
import com.ssa.entity.Projet;
import com.ssa.entity.SuiviChantier;
import com.ssa.service.impl.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projets")
public class ProjectController {
    
    @Autowired
    ProjetServiceImpl projetService;
    
    
    @GetMapping("")
    public String projets(Model model) {
        model.addAttribute("projets", projetService.findAll());
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
    
    @PostMapping("/add")
    public String add(Projet projet) {
        projet.setRef("aze75Z65");
        projet.getFicheProjet().setRef("aze75Z65");
        projetService.save(projet);
        return "redirect:/projets";
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
    
}
