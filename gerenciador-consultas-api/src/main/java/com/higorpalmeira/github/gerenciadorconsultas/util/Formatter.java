package com.higorpalmeira.github.gerenciadorconsultas.util;

public class Formatter {
	
	public static String clearCpfCepCrmTelefone(String input) {
		
		if (input == null) return null;
		
		if (input.isBlank()) return input;
		
		return input.replaceAll("[^A-Z0-9]", "");
		
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
	
	public static String ofTelefone(String telefone) {
		
		if (telefone == null || telefone.isBlank()) return telefone;
		
		String telefoneDigitos = telefone.replaceAll("[^\\d]", "");
		
		if (telefoneDigitos.length() != 11) return telefone;
		
		String regex = "^(\\d{2})(\\d{5})(\\d{4})$";
		String replacement = "($1) $2-$3";
		
		return telefoneDigitos.replaceAll(regex, replacement);
		
	}
	
	public static String ofCrm(String crm) {
		
		if (crm == null || crm.isBlank()) return crm;
		
		String crmCaracteres = crm.replaceAll("[^A-Z0-9]", "");
		
		if (crmCaracteres.length() != 11) return crm;
		
		String regex = "^([A-Z]{3})([A-Z]{2})(\\d{6})$";
    	String replacement = "$1/$2 $3";
    	
    	return crmCaracteres.replaceAll(regex, replacement);
		
	}

}
