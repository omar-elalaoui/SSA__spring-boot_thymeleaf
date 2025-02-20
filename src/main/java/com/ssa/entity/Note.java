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
public class Note {
    @Id @GeneratedValue
    private long id;
    private String note;
    private Date date;
}
