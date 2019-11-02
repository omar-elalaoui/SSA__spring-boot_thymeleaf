package com.ssa.service.impl;

import com.ssa.dao.LogConnexionRepository;
import com.ssa.dao.LogRepository;
import com.ssa.entity.Log;
import com.ssa.entity.LogConnexion;
import com.ssa.entity.User;
import com.ssa.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    
    @Autowired
    LogRepository logRepository;
    @Autowired
    LogConnexionRepository logConnexionRepository;
    @Autowired
    UserServiceImpl userService;
    
    @Override
    public void save(Log log) {
        logRepository.save(log);
    }
    
    @Override
    public void saveLogConnexion(LogConnexion logConnexion) {
        logConnexionRepository.save(logConnexion);
    }
    
    @Override
    public Log findById(long id) {
        return logRepository.findById(id).get();
    }
    
    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }
    
    @Override
    public List<LogConnexion> findAllConnexions() {
        return logConnexionRepository.findAll();
    }
    
    @Override
    public void modification_log(String projet) {
        Log log;
        if(userService.getcurrentUsername().equals("admin")){
            log= new Log(0, projet, "Modification", new Date(), "Admin");
        }else {
            User user= userService.findById(userService.getcurrentUsername());
            log= new Log(0, projet, "Modification", new Date(), user.getProfile().getPrenom()+" "+user.getProfile().getNom());
        }logRepository.save(log);
    }
    
    @Override
    public void ajout_log(String projet) {
        Log log;
        if(userService.getcurrentUsername().equals("admin")){
            log= new Log(0, projet, "Ajout", new Date(), "Admin");
        }else {
            User user= userService.findById(userService.getcurrentUsername());
            log= new Log(0, projet, "Ajout", new Date(), user.getProfile().getPrenom()+" "+user.getProfile().getNom());
        }
        logRepository.save(log);
    }
    
    @Override
    public void supp_log(String projet) {
        Log log;
        if(userService.getcurrentUsername().equals("admin")){
            log= new Log(0, projet, "Suppression", new Date(), "Admin");
        }else {
            User user= userService.findById(userService.getcurrentUsername());
            log= new Log(0, projet, "Suppression", new Date(), user.getProfile().getPrenom()+" "+user.getProfile().getNom());
        }
        logRepository.save(log);
    }
}
