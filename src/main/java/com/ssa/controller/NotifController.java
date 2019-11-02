package com.ssa.controller;

import com.ssa.dao.NotificationRepository;
import com.ssa.entity.Notification;
import com.ssa.service.impl.NotificationServiceImpl;
import com.ssa.service.impl.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notifs")
public class NotifController {
    
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    ProjetServiceImpl projetService;
    
    @GetMapping("")
    public String notifs(Model model) {
        List<Notification> notifList= notificationRepository.findByReportedIsFalse();
        model.addAttribute("notifs", notifList);
        return "notifs";
    }
    
    @GetMapping("/{id}/reporter")
    public String notifsc(Model model, @PathVariable(value="id") long id) {
        Notification notif= notificationService.findById(id);
        notif.setReported(true);
        notificationService.save(notif);
        return "redirect:/notifs";
    }
    
    @GetMapping("/reportes")
    public String notifsR(Model model) {
        List<Notification> notifList= notificationRepository.findByReportedIsTrue();
        model.addAttribute("notifs", notifList);
        return "notifsRepor";
    }
}
