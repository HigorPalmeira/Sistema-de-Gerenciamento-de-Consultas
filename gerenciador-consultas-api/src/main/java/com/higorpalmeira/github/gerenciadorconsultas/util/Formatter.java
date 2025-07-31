package com.higorpalmeira.github.gerenciadorconsultas.util;

public class Formatter {
	
	public static String clearCpfCepTelefone(String input) {
		
		if (input == null) return null;
		
		if (input.isBlank()) return input;
		
		return input.replaceAll("[ .()-]", "");
		
	}

}
