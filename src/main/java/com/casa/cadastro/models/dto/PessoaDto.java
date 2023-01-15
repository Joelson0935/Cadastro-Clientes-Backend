package com.casa.cadastro.models.dto;

import com.casa.cadastro.models.Pessoa;

public class PessoaDto {

	private String nome;
	private String sobrenome;
	private Integer idade;
	private String sexo;
	private String telefone;

	public PessoaDto(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.sobrenome = pessoa.getSobrenome();
		this.idade = pessoa.getIdade();
		this.sexo = pessoa.getSexo();
		this.telefone = pessoa.getTelefone();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
