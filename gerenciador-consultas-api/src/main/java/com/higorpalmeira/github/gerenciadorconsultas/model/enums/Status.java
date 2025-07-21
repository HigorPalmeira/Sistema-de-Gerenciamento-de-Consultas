package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;

public class Status {
	
	public enum TipoStatusConta {
		ATIVO("ATIVO"),
		INATIVO("INATIVO");
		
		private String tipo;
		
		TipoStatusConta(String tipo) {
			this.tipo = tipo;
		}
		
		public String getTipo() {
			return this.tipo;
		}
		
		public static TipoStatusConta fromTipo(String tipo) {
			for (TipoStatusConta status : TipoStatusConta.values()) {
				if (status.tipo.equalsIgnoreCase(tipo)) {
					return status;
				}
			}
			
			throw new InvalidDataException("O tipo não é compatível!");
		}
	}
	
	public enum TipoStatusConsulta {
		AGENDADA("AGENDADA"),
		REALIZADA("REALIZADA"),
		REAGENDADA("REAGENDADA"),
		CANCELADA("CANCELADA"),
		FALTA("FALTA"),
		INATIVA("INATIVA");
		
		private String tipo;
		
		TipoStatusConsulta(String tipo) {
			this.tipo = tipo;
		}
		
		public String getTipo() {
			return this.tipo;
		}
		
		public static TipoStatusConsulta fromTipo(String tipo) {
			for (TipoStatusConsulta status : TipoStatusConsulta.values()) {
				if (status.tipo.equalsIgnoreCase(tipo)) {
					return status;
				}
			}
			
			throw new InvalidDataException("O tipo não é compatível!");
		}
	}

}
