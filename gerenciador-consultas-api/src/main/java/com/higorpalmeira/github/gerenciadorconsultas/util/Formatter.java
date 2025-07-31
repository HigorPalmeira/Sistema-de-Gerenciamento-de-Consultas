package com.higorpalmeira.github.gerenciadorconsultas.util;

public class Formatter {
	
	public static String clearCpfCepTelefone(String input) {
		
		if (input == null) return null;
		
		if (input.isBlank()) return input;
		
		return input.replaceAll("[ .()-]", "");
		
	}
	
	public static String ofCpf(String cpf) {
		
		if (cpf == null || cpf.isBlank()) return cpf;
		
		String cpfDigitos = cpf.replaceAll("[^\\d]", "");
		
		if (cpfDigitos.length() != 11) return cpf;
		
		String regex = "^(\\d{3})(\\d{3})(\\d{3})(\\d{2})$";
		String replacement = "$1.$2.$3-$4";
		
		return cpfDigitos.replaceAll(regex, replacement);
		
	}
	
	public static String ofCep(String cep) {
		
		if (cep == null || cep.isBlank()) return cep;
		
		String cepDigitos = cep.replaceAll("[^\\d]", "");
		
		if (cepDigitos.length() != 8) return cep;
		
		String regex = "^(\\d{2})(\\d{3})(\\d{3})$";
		String replacement = "$1.$2-$3";
		
		return cepDigitos.replaceAll(regex, replacement);
		
	}

}
