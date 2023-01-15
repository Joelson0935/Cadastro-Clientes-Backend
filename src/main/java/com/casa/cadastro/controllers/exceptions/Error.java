package com.casa.cadastro.controllers.exceptions;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	private String err;
	private Integer codigo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	public Error() {
		super();
	}

	public Error(String err, Integer codigo, LocalDate data) {
		super();
		this.err = err;
		this.codigo = codigo;
		this.data = data;
	}

	public String getError() {
		return err;
	}

	public void setError(String error) {
		this.err = error;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}


}
