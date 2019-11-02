package com.ssa.controller;

import com.ssa.entity.Log;
import com.ssa.entity.LogConnexion;
import com.ssa.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    LogServiceImpl logService;
    
    @GetMapping("/actions")
    public String actions(Model model) {
        List<Log> logList= logService.findAll();
        Collections.reverse(logList);
        model.addAttribute("ActionsLogs", logList);
        return "log_actions";
    }
    
    @GetMapping("/connexions")
    public String connexions(Model model) {
        List<LogConnexion> logConnexionList= logService.findAllConnexions();
        Collections.reverse(logConnexionList);
        model.addAttribute("connexionsLog", logConnexionList);
        return "log_connexions";
    }
    
}
