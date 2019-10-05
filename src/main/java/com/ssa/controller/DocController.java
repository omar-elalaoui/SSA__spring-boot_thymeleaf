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
    
}
