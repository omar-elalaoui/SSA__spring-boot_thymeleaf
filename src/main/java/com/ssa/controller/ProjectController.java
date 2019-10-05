package com.ssa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
    @GetMapping("")
    public String projects() {
        return "projets";
    }
    
    @GetMapping("add")
    public String add() {
        return "project_add";
    }
    
    @GetMapping("/fiche")
    public String project_fiche(Model model) {
        return "project_fiche";
    }
    
    @GetMapping("/description")
    public String project_desc(Model model) {
        return "project_desc";
    }
    
    @GetMapping("/suivi_chantier")
    public String project_suivi(Model model) {
        return "project_suivi_chantier";
    }
    
    @GetMapping("/pices")
    public String project_pices(Model model) {
        return "project_pices";
    }
    
}
