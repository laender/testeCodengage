package com.teste.enums;

public enum EnumTipoMovimento {

	
	ENTRADA("ENTRADA"),
	SAIDA("SAIDA");
	
	private String value;
	
    EnumTipoMovimento(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
