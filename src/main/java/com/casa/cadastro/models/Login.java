package com.casa.cadastro.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.casa.cadastro.models.enums.Perfil;

@Entity
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Campo Obrigatório!")
	@Email(message = "Insira um email válido!")
	private String email;
	@NotEmpty(message = "Insira uma senha!")
	@Size(min = 9, max = 50, message = "Insira mais de 9 e menos de 50 caracteres!")
	private String senha;
	
	@Enumerated(EnumType.STRING) 
	private Perfil perfil;

	public Login() {
	}

	public Login(Long id, String email, String senha, Perfil perfil) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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
		Login other = (Login) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", senha=" + senha + ", perfil=" + perfil + "]";
	}

}
