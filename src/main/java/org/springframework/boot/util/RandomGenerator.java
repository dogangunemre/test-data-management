package org.springframework.boot.util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

@UtilityClass
public class RandomGenerator {

    String characters = "abcdefghijklmnopqrstuvwxyz1234567890";
    SecureRandom secureRandom = new SecureRandom();

    public static String getRandomTCKN() {
        int[] tckn = new int[11];
        tckn[0] = secureRandom.nextInt(8) + 1;
        for (int i = 1; i < 9; i++) {
            tckn[i] = secureRandom.nextInt(10);
        }
        int oddSum = tckn[0] + tckn[2] + tckn[4] + tckn[6] + tckn[8];
        int evenSum = tckn[1] + tckn[3] + tckn[5] + tckn[7];
        tckn[9] = ((oddSum * 7) - evenSum) % 10;
        int totalSum = 0;
        for (int i = 0; i < 10; i++) {
            totalSum += tckn[i];
        }
        tckn[10] = totalSum % 10;
        StringBuilder sb = new StringBuilder();
        for (int digit : tckn) {
            sb.append(digit);
        }
        return sb.toString();
    }

    public static String generateRandomPhoneNumber() {
        String[] areaCodes = {"530", "531", "532", "533", "534", "535", "536", "537", "538", "539",
                "501", "505", "506", "507", "551", "552", "553", "554", "555"};
        String areaCode = areaCodes[secureRandom.nextInt(areaCodes.length)];
        int numberPart1 = secureRandom.nextInt(900) + 100;
        int numberPart2 = secureRandom.nextInt(9000) + 1000;
        return areaCode + numberPart1 + numberPart2;
    }

    public static String generateRandomString(int x) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < x; i++) {
            prefix.append(characters.charAt(secureRandom.nextInt(characters.length())));
        }
        return prefix + "";
    }

}