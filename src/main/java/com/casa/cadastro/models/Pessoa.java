package com.casa.cadastro.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 4, max = 18, message = "Mínimo 4 e máximo 18 caracteres")
	private String nome;
	@Size(min = 15, max = 60, message = "Mínimo 15 e máximo 60 caracteres")
	private String sobrenome;
	@Min(value = 1, message = "Idade mínima de 1 ano")
	@Max(value = 120, message = "Idade máxima de 120 anos.")
	private Integer idade;
	@Size(max = 1, min = 1, message = "Aceita apenas Uma Letra")
	private String sexo;
	@Pattern(regexp = "([1-9]{2})([ ])?([0-9]{4,5})([-])?([0-9]{4})", message = "Siga o Padrão: 99 99999-9999")
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	public Pessoa() {
		super();
	}

	public Pessoa(String nome, String sobrenome, Integer idade, String sexo, String telefone, Endereco endereco) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.sexo = sexo;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Pessoa(Long id, String nome, String sobrenome, Integer idade, String sexo, String telefone,
			Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idade = idade;
		this.sexo = sexo;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade + ", sexo="
				+ sexo + ", telefone=" + telefone + ", endereco=" + endereco + "]";
	}

}
