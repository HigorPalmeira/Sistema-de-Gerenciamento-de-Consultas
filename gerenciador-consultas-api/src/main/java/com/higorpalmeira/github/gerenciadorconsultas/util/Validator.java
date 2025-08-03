package com.higorpalmeira.github.gerenciadorconsultas.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	private static final String EMAIL_REGEX = 
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final Pattern PATTERN_EMAIL = Pattern.compile(EMAIL_REGEX);
	
	private static final String CRM_REGEX = "^CRM/([A-Z]{2})\\s(\\d{6})$";
	private static final Pattern PATTERN_CRM = Pattern.compile(CRM_REGEX);
	
	private static final List<String> UFS_VALIDAS = Arrays.asList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", 
            "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", 
            "RR", "SC", "SP", "SE", "TO"
    );
	
	private static final BigDecimal VALOR_MAXIMO = new BigDecimal("99999.99");
	
	public static boolean CPFValidation(final String input) {
		
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
		
		for (int i=0; i<9; i++) {
			firstDigit += Character.getNumericValue(arrCpf[i]) * j--;
		}
		firstDigit = (firstDigit * 10) % 11;
		
		if (firstDigit != Character.getNumericValue(arrCpf[9])) {
			return false;
		}
		
		int secondDigit = 0;
		j = 11;
		
		for (int i=0; i<10; i++) {
			secondDigit += Character.getNumericValue(arrCpf[i]) * j--;
		}
		secondDigit = (secondDigit * 10) % 11;
		
		if (secondDigit != Character.getNumericValue(arrCpf[10])) {
			return false;
		}
		
		char initialChar = arrCpf[0];
		for (int i=1; i<arrCpf.length; i++) {
			if (initialChar != arrCpf[i]) {
				return true;
			}
		}
		
		return false;
	}

	public static boolean EmailValidation(final String input) {
		
		if (input == null) {
			return false;
		}
		
		Matcher matcher = PATTERN_EMAIL.matcher(input);
		
		return matcher.matches();
		
	}
	
	public static boolean CRMValidation(final String input) {
		
		if (input == null || input.isBlank()) {
			return false;
		}
		
		Matcher matcher = PATTERN_CRM.matcher(input.trim());
		
		if (!matcher.matches()) {
			return false;
		}
		
		String uf = matcher.group(1);
		String numero = matcher.group(2);
		
		try {
			Integer.parseInt(numero);
		} catch(NumberFormatException e) {
			return false;
		}
		
		if (!UFS_VALIDAS.contains(uf)) {
			return false;
		}
		
		return true;
		
	}
	
	public static boolean ValorValidation(BigDecimal valor) {
		
		if (valor == null) return false;
		
		if (valor.compareTo(BigDecimal.ZERO) < 0) return false;
		
		if (valor.scale() > 2) return false;
		
		if (valor.compareTo( VALOR_MAXIMO ) > 0) return false;
		
		return true;
		
	}
	
}
