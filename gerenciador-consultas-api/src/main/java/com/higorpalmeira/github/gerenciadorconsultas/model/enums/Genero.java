package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

public class Genero {
	
	public enum TipoGenero {
		FEMININO("FEMININO"),
		MASCULINO("MASCULINO"),
		NAO_DEFINIDO("NÃO DEFINIDO");
		
		private String tipo;
		
		TipoGenero(String tipo) {
			this.tipo = tipo;
		}
		
		public String getTipo() {
			return this.tipo;
		}
		
		public static TipoGenero fromTipo(String tipo) {
			for (TipoGenero genero : TipoGenero.values()) {
				if (genero.tipo.equalsIgnoreCase(tipo)) {
					return genero;
				}
			}
			
			throw new UnsupportedOperationException("O tipo não é compatível!");
		}
	}

}
