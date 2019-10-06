package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase2MultipartFiles {
    private MultipartFile releve_existant;
    private MultipartFile photo_site;
    private MultipartFile esquisse;
    private MultipartFile plan_dwg;
}
