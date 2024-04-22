package com.iBME.emg_label_tool.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Random;

public class RandomString {

    public static String generateRandomString(int num) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}|;:',<.>/?";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

        StringBuilder stringBuilder = new StringBuilder();

        if (num > 0 && num <= characters.length()) {
            for (int i = 0; i <num; i++) {
                Character character = characters.charAt(new Random().nextInt(characters.length()));
                stringBuilder.append(character);
            }

            return stringBuilder.toString();
        }

        return "";
    }
}
