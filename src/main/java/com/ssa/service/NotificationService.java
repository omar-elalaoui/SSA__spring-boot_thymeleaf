package com.ssa.service;

import com.ssa.entity.Notification;

import java.util.List;

public interface NotificationService {
    public void save(Notification notification);
    public void reporterNotif(long id);
    public Notification findById(long id);
    public void deleteById(long id);
    public List<Notification> findAll();
}
