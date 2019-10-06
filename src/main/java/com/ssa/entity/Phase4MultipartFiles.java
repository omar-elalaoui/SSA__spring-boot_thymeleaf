package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase4MultipartFiles {
    private MultipartFile photo_acheve;
    private MultipartFile fermeture_chantier;
    private MultipartFile attestation_fin_travaux;
}
