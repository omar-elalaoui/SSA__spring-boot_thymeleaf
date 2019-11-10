package com.ssa.controller;

import com.ssa.dao.NotificationRepository;
import com.ssa.service.impl.ProjetServiceImpl;
import com.ssa.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @Autowired
    ProjetServiceImpl projetService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    NotificationRepository notificationRepository;
    
    @GetMapping("/dash")
    public String dash(Model model) {
        model.addAttribute("nbr_projets", projetService.findAll().size());
        model.addAttribute("nbr_users", userService.findAll().size());
        model.addAttribute("nbr_notifs", notificationRepository.findByReportedIsFalse().size());
        model.addAttribute("nbr_notifs_reported", notificationRepository.findByReportedIsTrue().size());
        return "dashboard";
    }
    
    @GetMapping("")
    public String contrat() {
        return "redirect:/dash";
    }
    
    
}
