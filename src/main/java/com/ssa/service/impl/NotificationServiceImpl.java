package com.ssa.service.impl;

import com.ssa.dao.NotificationRepository;
import com.ssa.entity.Notification;
import com.ssa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    
    @Autowired
    NotificationRepository notificationRepository;
    
    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
    
    @Override
    public void reporterNotif(long id) {
        Notification notification=findById(id);
        notification.setReported(true);
        save(notification);
    }
    
    @Override
    public Notification findById(long id) {
        return notificationRepository.findById(id).get();
    }
    
    @Override
    public void deleteById(long id) {
        notificationRepository.deleteById(id);
    }
    
    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }
}
