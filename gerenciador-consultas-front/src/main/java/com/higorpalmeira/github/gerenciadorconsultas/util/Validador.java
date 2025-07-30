/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.util;

import java.util.List;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author higor
 */
public class Validador {

    private static final String CRM_REGEX = "^CRM/([A-Z]{2})\\s(\\d{6})$";
    private static final Pattern PATTERN_CRM = Pattern.compile(CRM_REGEX);

    private static final List<String> UFS_VALIDAS = Arrays.asList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT",
            "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO",
            "RR", "SC", "SP", "SE", "TO"
    );

    private static final String EMAIL_REGEX
            = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);

    private static final String TELEFONE_REGEX = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$";
    private static final Pattern PATTERN_TELEFONE = Pattern.compile(TELEFONE_REGEX);

    private static final String CEP_REGEX = "^(\\d{2}\\.\\d{3}-\\d{3}|\\d{5}-\\d{3}|\\d{8})$";
    private static final Pattern PATTERN_CEP = Pattern.compile(CEP_REGEX);

    public static boolean isCrm(final String crm) {

        if (crm == null || crm.isBlank()) {
            return false;
        }

        Matcher matcher = PATTERN_CRM.matcher(crm.trim());

        if (!matcher.matches()) {
            return false;
        }

        String uf = matcher.group(1).toUpperCase();
        String numero = matcher.group(2);

        try {
            Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return false;
        }

        if (!UFS_VALIDAS.contains(uf)) {
            return false;
        }

        return true;

    }

    public static boolean isEmail(final String input) {

        if (input == null) {
            return false;
        }

        Matcher matcher = PATTERN_EMAIL.matcher(input);

        return matcher.matches();

    }

    public static boolean isTelefone(final String input) {

        if (input == null) {
            return false;
        }

        Matcher matcher = PATTERN_TELEFONE.matcher(input);

        return matcher.matches();

    }

    public static boolean isCep(final String input) {

        if (input == null) {
            return false;
        }

        Matcher matcher = PATTERN_CEP.matcher(input);

        return matcher.matches();

    }

    public static boolean isCpf(final String input) {

        if (input == null || input.isBlank()) {
            return false;
        }

        String cpf = input.trim().replace(".", "").replace("-", "");
        if (cpf.length() != 11) {
            return false;
        }

        char[] arrCpf = cpf.toCharArray();
        int firstDigit = 0;
        int j = 10;

        for (int i = 0; i < 9; i++) {
            firstDigit += Character.getNumericValue(arrCpf[i]) * j--;
        }
        firstDigit = (firstDigit * 10) % 11;

        if (firstDigit != Character.getNumericValue(arrCpf[9])) {
            return false;
        }

        int secondDigit = 0;
        j = 11;

        for (int i = 0; i < 10; i++) {
            secondDigit += Character.getNumericValue(arrCpf[i]) * j--;
        }
        secondDigit = (secondDigit * 10) % 11;

        if (secondDigit != Character.getNumericValue(arrCpf[10])) {
            return false;
        }

        char initialChar = arrCpf[0];
        for (int i = 1; i < arrCpf.length; i++) {
            if (initialChar != arrCpf[i]) {
                return true;
            }
        }

        return false;
    }

}
