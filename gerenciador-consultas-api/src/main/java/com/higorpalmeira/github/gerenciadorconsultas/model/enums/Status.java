package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;

public class Status {
	
	public enum StatusAccountType {
		ACTIVE("ACTIVE"),
		INACTIVE("INACTIVE");
		
		private String type;
		
		StatusAccountType(String aType) {
			this.type = aType;
		}
		
		public static StatusAccountType fromType(String aType) {
			for (StatusAccountType status : StatusAccountType.values()) {
				if (status.type.equalsIgnoreCase(aType)) {
					return status;
				}
			}
			
			throw new InvalidDataException("The type is not supported!");
		}
	}
	
	public enum StatusConsultationType {
		SCHEDULED("SCHEDULED"),
		HELD("HELD"),
		RESCHEDULED("RESCHEDULED"),
		CANCELED("CANCELED"),
		MISSING("MISSING");
		
		private String type;
		
		StatusConsultationType(String aType) {
			this.type = aType;
		}
		
		public static StatusConsultationType fromType(String aType) {
			for (StatusConsultationType status : StatusConsultationType.values()) {
				if (status.type.equalsIgnoreCase(aType)) {
					return status;
				}
			}
			
			throw new InvalidDataException("The type is not supported!");
		}
	}

}
