package com.ssa.controller;

import com.ssa.dao.NotificationRepository;
import com.ssa.entity.Notification;
import com.ssa.entity.Projet;
import com.ssa.service.impl.NotificationServiceImpl;
import com.ssa.service.impl.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class NotifRestController {
    
    @Autowired
    NotificationServiceImpl notificationService;
    @Autowired
    ProjetServiceImpl projetService;
    @Autowired
    NotificationRepository notificationRepository;
    
    @GetMapping("/notifNumber")
    public int getNotifNb(){
        return notificationRepository.findByReportedIsFalseAndDisabledFalse().size();
    }
    
    @GetMapping("/projetNumber")
    public int getProjetNb(){
        return projetService.findAll().size();
    }
    
    @GetMapping("/notifList")
    public List<Notification> getNotifls(){
        return notificationRepository.findByReportedIsFalseAndDisabledFalse();
    }
    
    @GetMapping("/notif_reported_list")
    public List<Notification> getNotiflsrrr(){
        return notificationRepository.findByReportedIsTrueAndDisabledFalse();
    }
    
    @GetMapping("/projetList")
    public List<Projet> getprojetList(){
        List<Projet> projetList= projetService.findAll();
        projetList.stream().forEach(projet -> {
            projet.setSuiviChantier(null);
            projet.setDescProjet(null);
        });
        return projetList;
    }
    @GetMapping("/{ref}/notif")
    public int getprhh(@PathVariable("ref") String ref){
        if(notificationRepository.findByProjetid(ref) == null){
            Projet projet= projetService.findById(ref);
            Notification notification= new Notification(0, ref, projet.getFicheProjet().getPrenom()+" "+projet.getFicheProjet().getNom(), new Date(), new Date(), false, false);
            notificationService.save(notification);
        }
        return 1;
    }
    
    
    @GetMapping("/{id}/notif/reporter")
    public int getprhdh(@PathVariable("id") long id){
            Notification notification= notificationService.findById(id);
            notification.setReport_date(null);
            notification.setReport_date(new Date());
            notification.setReported(false);
            notificationService.save(notification);
        return 1;
    }
    
    @GetMapping("/{ref}/notif/delete")
    public int getprhdhg(@PathVariable("ref") String ref){
        Notification notification= notificationRepository.findByProjetid(ref);
        if(notification != null){ notificationService.deleteById(notification.getId()); }
        return 1;
    }
    
    
}
