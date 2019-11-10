package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Notification {
    
    @Id
    @GeneratedValue
    private long id;
    private String projetid;
    private String projetPerName;
    private Date date;
    private Date report_date;
    private boolean reported;
}
