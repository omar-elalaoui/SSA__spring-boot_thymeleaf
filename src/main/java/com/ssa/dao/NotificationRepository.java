package com.ssa.dao;

import com.ssa.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification, Long > {
    public Notification findByProjetid(String ref);
    public List<Notification> findByReportedIsFalseAndDisabledFalse();
    public List<Notification> findByReportedIsTrueAndDisabledFalse();
}
