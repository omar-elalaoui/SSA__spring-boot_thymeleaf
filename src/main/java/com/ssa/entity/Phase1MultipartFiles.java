package com.ssa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phase1MultipartFiles {
    private MultipartFile cin_passeport;
    private MultipartFile contrat_arch;
    private MultipartFile demande;
    private MultipartFile declaration;
    private MultipartFile certificat_prop;
    private MultipartFile plan;
    private MultipartFile liste_coord;
    private MultipartFile plan_cote;
    private MultipartFile note_reinseignement;
}
