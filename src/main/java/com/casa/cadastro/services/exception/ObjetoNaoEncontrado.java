package com.casa.cadastro.services.exception;

public class ObjetoNaoEncontrado extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontrado(String mensagem) {
		super(mensagem);
	}

}
