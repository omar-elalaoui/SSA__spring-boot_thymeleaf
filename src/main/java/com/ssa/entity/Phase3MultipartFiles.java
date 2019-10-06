package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase3MultipartFiles {
    private MultipartFile autorization;
    private MultipartFile pv_commition;
    private MultipartFile plan_approuve;
    private MultipartFile attestation_impl;
    private MultipartFile plan_beton_arme;
    private MultipartFile pv_suivi;
    private MultipartFile photo_exec;
}
