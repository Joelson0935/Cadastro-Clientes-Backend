package com.casa.cadastro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.cadastro.models.Login;
import com.casa.cadastro.repositorys.LoginRepository;
import com.casa.cadastro.services.Servico;

@Service
public class LoginService implements Servico<Login> {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public Login salvar(Login login) {
		login = loginRepository.save(login);
		return login;
	}

	@Override
	public Login atualizar(Long id, Login login) {
		Login loginEncontrado = loginRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		login.setId(id);
		loginEncontrado = login;
		loginEncontrado = loginRepository.save(loginEncontrado);
		return loginEncontrado;
	}

	@Override
	public Login buscarPorId(Long id) {
		Login login = loginRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return login;
	}

	public Login buscarPorEmail(String email) {
		Login login = loginRepository.buscarPorEmail(email);
		if (login != null) {
			new RuntimeException("Email Não Encontrado!");
		}
		return login;
	}

	@Override
	public List<Login> buscarListaCompleta() {
		List<Login> logins = loginRepository.findAll();
		return logins;
	}

	@Override
	public Page<Login> buscarListaPaginada(Pageable pageable) {
		Page<Login> logins = loginRepository.findAll(pageable);
		return logins;
	}

	@Override
	public void deletar(Long id) {
		loginRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		loginRepository.deleteById(id);
	}

}
