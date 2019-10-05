package com.ssa.service;

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





}
