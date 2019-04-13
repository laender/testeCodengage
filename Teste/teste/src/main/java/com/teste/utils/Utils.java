package com.teste.utils;

import java.util.Collection;

public abstract class Utils {

	public static final String  APPLICATION_JSON = "application/json";
	
	public static boolean empty(String val) {
		return val == null || val.trim().isEmpty();
	}

	public static boolean empty(Collection val) {
		return val == null || val.isEmpty();
	}

	public static <T> T coalesce(T valor, T padrao) {
		if (valor instanceof String && (valor + "").trim().isEmpty()) {
			valor = null;
		}
		if (valor == null) {
			return padrao;
		}
		return valor;
	}
}
