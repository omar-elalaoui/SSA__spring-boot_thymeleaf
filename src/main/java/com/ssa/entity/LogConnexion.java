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
public class LogConnexion {
    @Id
    @GeneratedValue
    private long id;
    private Date date;
    private String user;
}
