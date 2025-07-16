package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

public class Gender {
	
	public enum GenderType {
		FEMALE("FEMALE"),
		MALE("MALE"),
		NOTDEFINED("NOT DEFINED");
		
		private String type;
		
		GenderType(String aType) {
			this.type = aType;
		}
		
		public String getType() {
			return this.type;
		}
		
		public static GenderType fromType(String aType) {
			for (GenderType gender : GenderType.values()) {
				if (gender.type.equalsIgnoreCase(aType)) {
					return gender;
				}
			}
			
			throw new UnsupportedOperationException("The type is not supported!");
		}
	}

}
