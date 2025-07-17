/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.higorpalmeira.github.gerenciadorconsultas.util;

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

}
