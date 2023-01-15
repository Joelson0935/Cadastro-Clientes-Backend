package com.casa.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.cadastro.models.Login;
import com.casa.cadastro.repositorys.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public Login salvar(Login login) {
		login = loginRepository.save(login);
		return login;
	}

	public Login atualizar(Login login, Long id) {
		Login l = buscarPorId(id);
		if (l != null) {
			login = loginRepository.save(login);
			return login;
		}
		return null;
	}

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

	public List<Login> buscarListaCompleta() {
		List<Login> logins = loginRepository.findAll();
		return logins;
	}

	public Page<Login> buscarListaPaginada(Pageable pageable) {
		Page<Login> logins = loginRepository.findAll(pageable);
		return logins;
	}

	public void deletar(Long id) {
		loginRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		loginRepository.deleteById(id);
	}

}
