package com.ssa.service;

import com.ssa.entity.Phase1;
import com.ssa.entity.Phase2;
import com.ssa.entity.Phase3;
import com.ssa.entity.Phase4;

import java.security.SecureRandom;

public class SsaUtil {

    public static String generateRef() {
        int length =15;
        String CHAR_min = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_maj = CHAR_min.toUpperCase();
        String nombre = "0123456789";

        String Random_set_prep = CHAR_min + CHAR_maj + nombre;
        SecureRandom random = new SecureRandom();


        StringBuilder ref = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(Random_set_prep.length());
            char rndChar = Random_set_prep.charAt(rndCharAt);

            ref.append(rndChar);
        }

        return ref.toString();
    }
    
    public static boolean isPhase1Full(Phase1 phase1){
        if(phase1.getCin_passeport() != null){
            if(phase1.getCin_passeport() != null){
                if(phase1.getContrat_arch() != null){
                    if(phase1.getDemande() != null){
                        if(phase1.getDeclaration() != null){
                            if(phase1.getCertificat_prop() != null){
                                if(phase1.getPlan() != null){
                                    if(phase1.getListe_coord() != null){
                                        if(phase1.getPlan_cote() != null){
                                            if(phase1.getNote_reinseignement() != null){
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isPhase2Full(Phase2 phase2){
        if(phase2.getReleve_existant() != null){
            if(phase2.getPhoto_site() != null){
                if(phase2.getEsquisse() != null){
                    if(phase2.getPlan_dwg() != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isPhase3Full(Phase3 phase3){
        if(phase3.getAutorization() != null){
            if(phase3.getPv_commition() != null){
                if(phase3.getPlan_approuve() != null){
                    if(phase3.getAttestation_impl() != null){
                        if(phase3.getPlan_beton_arme() != null){
                            if(phase3.getPv_commition() != null){
                                if(phase3.getPhoto_exec() != null){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean isPhase4Full(Phase4 phase4){
        if(phase4.getPhoto_acheve() != null){
            if(phase4.getFermeture_chantier() != null){
                if(phase4.getAttestation_fin_travaux() != null){
                    return true;
                }
            }
        }
        return false;
    }
    

}
