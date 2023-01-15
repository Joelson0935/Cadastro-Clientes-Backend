package com.casa.cadastro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casa.cadastro.models.Login;
import com.casa.cadastro.services.LoginService;

@RestController
@RequestMapping("/Login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/Salvar")
	public ResponseEntity<Login> Salvar(@Valid @RequestBody Login login) {
		login = loginService.salvar(login);
		return new ResponseEntity<Login>(login, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Login> buscarPorId(@PathVariable Long id) {
		Login login = loginService.buscarPorId(id);
		if (login != null) {
			return ResponseEntity.ok(login);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/BuscarPorEmail")
	public ResponseEntity<Login> buscarPorEmail(@RequestParam(name = "email") String email) {
		Login login = loginService.buscarPorEmail(email);
		if (login != null) {
			return ResponseEntity.ok(login);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<Login>> buscarListaCompleta() {
		List<Login> logins = loginService.buscarListaCompleta();
		return ResponseEntity.ok(logins);
	}

	@GetMapping("/BuscaPaginada")
	public ResponseEntity<Page<Login>> buscarListaPaginada(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Login> logins = loginService.buscarListaPaginada(pageable);
		if (logins == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(logins);
	}

	@PutMapping("/Atualizar/{loginId}")
	public ResponseEntity<Login> atualizarPorId(@Valid @PathVariable Long loginId,
			@RequestBody Login login) {
		Login l = loginService.buscarPorId(loginId);
		if (l != null) {
			login.setId(loginId);
			login = loginService.atualizar(login, loginId);
			return ResponseEntity.ok(login);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletarLoginPorId(@RequestParam(name = "deleteId") Long deleteId) {
		Login login = loginService.buscarPorId(deleteId);
		if (login == null) {
			return ResponseEntity.notFound().build();
		}
		loginService.deletar(deleteId);
		return ResponseEntity.noContent().build();
	}

}
