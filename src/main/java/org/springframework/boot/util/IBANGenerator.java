package org.springframework.boot.util;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class IBANGenerator {
    SecureRandom secureRandom = new SecureRandom();

    /**
     * IBAN üretme metodu.
     *
     * @param accountNumber  Hesap numarası (ör. "34805").
     * @param currencySuffix Para birimi kodu (ör. "00101" - dolar, "00102" - euro, "00103" - altın).
     * @return Üretilen IBAN.
     */
    public static String generateIBAN(String accountNumber, String currencySuffix) {
        String countryCode = "TR";        // Ülke kodu
        String bankCode = "00213";        // Banka kodu

        String basicIBAN = bankCode + "0000000" + accountNumber + currencySuffix;

        String convertedCountryCode = String.valueOf((countryCode.charAt(0) - 'A' + 10))
                + String.valueOf((countryCode.charAt(1) - 'A' + 10));

        String tempIBAN = basicIBAN + convertedCountryCode + "00";

        BigInteger ibanNumber = new BigInteger(tempIBAN);
        int checkDigits = 98 - ibanNumber.mod(BigInteger.valueOf(97)).intValue();

        String formattedCheckDigits = String.format("%02d", checkDigits);
        return countryCode + formattedCheckDigits + basicIBAN;
    }

    public static String generateRandomTRIBAN() {

        List<String> bankNames = new ArrayList<>(BANK_CODES.keySet());
        String randomBankName = bankNames.get(secureRandom.nextInt(bankNames.size()));
        String bankCode = BANK_CODES.get(randomBankName);

        String reservedField = "0";
        String accountNumber = String.format("%016d", secureRandom.nextLong(10000000000000000L));
        String bban = bankCode + reservedField + accountNumber;

        String temp = bban + "TR00";
        StringBuilder numericString = new StringBuilder();
        for (char c : temp.toCharArray()) {
            if (Character.isLetter(c)) {
                numericString.append(Character.getNumericValue(c));
            } else {
                numericString.append(c);
            }
        }
        long remainder = 0;
        for (int i = 0; i < numericString.length(); i++) {
            int digit = Character.getNumericValue(numericString.charAt(i));
            remainder = (remainder * 10 + digit) % 97;
        }
        int checkDigits = 98 - (int) remainder;
        return "TR" + String.format("%02d", checkDigits) + bban;
    }

    private static final Map<String, String> BANK_CODES = new HashMap<>();

    static {
        BANK_CODES.put("HALKBANK", "00124");
        BANK_CODES.put("VAKIFBANK", "00115");
        BANK_CODES.put("AKBANK", "00046");
        BANK_CODES.put("ISBANK", "00064");
        BANK_CODES.put("YAPIKREDI", "00067");
        BANK_CODES.put("QNB", "00103");
        BANK_CODES.put("DENIZBANK", "00134");
        BANK_CODES.put("TEB", "00032");
        BANK_CODES.put("SEKERBANK", "00059");
        BANK_CODES.put("ING", "00099");
        BANK_CODES.put("ALBARAKA", "00203");
        BANK_CODES.put("KUVEYTTURK", "00205");
        BANK_CODES.put("TURKIYEFINANS", "00206");
        BANK_CODES.put("ODEABANK", "00146");
        BANK_CODES.put("HSBC", "00123");
        BANK_CODES.put("CITIBANK", "00092");
        BANK_CODES.put("TURKLAND", "00129");
        BANK_CODES.put("ALTERNATIF", "00121");
        BANK_CODES.put("ARAPTURK", "00116");
        BANK_CODES.put("ZIRAAT2", "00100");
    }

}
