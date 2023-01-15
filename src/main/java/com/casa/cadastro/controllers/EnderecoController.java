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

import com.casa.cadastro.models.Endereco;
import com.casa.cadastro.services.EnderecoService;

@RestController
@RequestMapping("/Endereco")
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping("/Salvar")
	public ResponseEntity<Endereco> Salvar(@Valid @RequestBody Endereco endereco) {
		endereco = enderecoService.salvar(endereco);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
		Endereco endereco = enderecoService.buscarPorId(id);
		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<Endereco>> buscarListaCompleta() {
		List<Endereco> enderecos = enderecoService.buscarListaCompleta();
		return ResponseEntity.ok(enderecos);
	}

	@GetMapping("/BuscaPaginada")
	public ResponseEntity<Page<Endereco>> buscarListaPaginada(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Endereco> enderecos = enderecoService.buscarListaPaginada(pageable);
		if (enderecos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(enderecos);
	}

	@PutMapping("/Atualizar/{enderecoId}")
	public ResponseEntity<Endereco> atualizarPorId(@Valid @PathVariable Long enderecoId,
			@RequestBody Endereco endereco) {
		Endereco e = enderecoService.buscarPorId(enderecoId);
		if (e != null) {
			endereco.setId(enderecoId);
			endereco = enderecoService.atualizar(endereco, enderecoId);
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletarEnderecoPorId(@RequestParam(name = "deleteId") Long deleteId) {
		Endereco endereco = enderecoService.buscarPorId(deleteId);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		enderecoService.deletar(deleteId);
		return ResponseEntity.noContent().build();
	}

}
