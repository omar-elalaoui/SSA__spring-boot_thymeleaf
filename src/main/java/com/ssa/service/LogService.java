package com.ssa.service;

import com.ssa.entity.Log;
import com.ssa.entity.LogConnexion;

import java.util.List;


public interface LogService {
    public void save(Log log);
    public void saveLogConnexion(LogConnexion logConnexion);
    public Log findById(long id);
    public List<Log> findAll();
    public List<LogConnexion> findAllConnexions();
    public void modification_log(String projet);
    public void ajout_log(String projet);
    public void supp_log(String projet);
}
