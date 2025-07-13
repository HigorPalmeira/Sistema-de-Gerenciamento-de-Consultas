package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

public class Status {
	
	public enum StatusType {
		ACTIVE("ACTIVE"),
		INACTIVE("INACTIVE");
		
		private String type;
		
		StatusType(String aType) {
			this.type = aType;
		}
		
		public static StatusType fromType(String aType) {
			for (StatusType status : StatusType.values()) {
				if (status.type.equalsIgnoreCase(aType)) {
					return status;
				}
			}
			
			throw new UnsupportedOperationException("The type is not supported!");
		}
	}

}
