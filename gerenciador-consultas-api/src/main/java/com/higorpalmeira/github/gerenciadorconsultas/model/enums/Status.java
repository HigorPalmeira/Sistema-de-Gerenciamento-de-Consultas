package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

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
			
			throw new UnsupportedOperationException("The type is not supported!");
		}
	}

}
