package com.casa.cadastro.models.enums;

public enum Perfil {

	USUARIO(1), ADMINISTRADOR(2);

	private int numero;

	private Perfil(int numero) {
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

}
