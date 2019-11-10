package com.ssa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/documents")
public class DocController {
    
    @GetMapping("/contrat")
    public String contrat() {
        return "doc_contrat";
    }
    
    @GetMapping("/demande")
    public String demande() {
        return "doc_demande";
    }
    
    @GetMapping("/declaration")
    public String declaration() {
        return "doc_declaration";
    }
    
    @GetMapping("/fiche_rens_11")
    public String fiche_tch_11() { return "doc_fiche_rens_11"; }
    
    @GetMapping("/fin_travaux_12")
    public String fiche_tch_12() { return "doc_fin_travaux_12"; }
    
    @GetMapping("/fiche_tch_13")
    public String fiche_tch_13() { return "doc_fiche_tch_13"; }
    
    @GetMapping("/fiche_tch_15")
    public String fiche_tch_15() { return "doc_fiche_tch_15"; }
    
    @GetMapping("/bordereau_14")
    public String fiche_tch_14() { return "doc_bordereau_14"; }
    
}
