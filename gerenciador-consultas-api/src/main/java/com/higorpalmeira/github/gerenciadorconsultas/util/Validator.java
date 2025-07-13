package com.higorpalmeira.github.gerenciadorconsultas.util;

public class Validator {
	
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

}
