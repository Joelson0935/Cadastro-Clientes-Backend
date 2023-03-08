package com.casa.cadastro.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.casa.cadastro.models.Pessoa;
import com.casa.cadastro.models.dto.PessoaDto;
import com.casa.cadastro.services.impl.PessoaService;

@RestController
@RequestMapping("/Pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/Salvar")
	public ResponseEntity<Pessoa> Salvar(@Valid @RequestBody Pessoa pessoa) {
		pessoa = pessoaService.salvar(pessoa);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.buscarPorId(id);
		if (pessoa != null) {
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<PessoaDto>> buscarListaCompleta() {
		List<Pessoa> pessoas = pessoaService.buscarListaCompleta();
		List<PessoaDto> pessoasDto = pessoas.stream().map(p -> new PessoaDto(p)).collect(Collectors.toList());
		return ResponseEntity.ok(pessoasDto);
	}

	@GetMapping("/BuscaPaginada")
	public ResponseEntity<Page<PessoaDto>> buscarListaPaginada(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Pessoa> pessoas = pessoaService.buscarListaPaginada(pageable);
		Page<PessoaDto> pessoasDto = pessoas.map(pessoa -> new PessoaDto(pessoa));
		if (pessoasDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoasDto);
	}

	@PutMapping("/Atualizar/{pessoaId}")
	public ResponseEntity<Pessoa> atualizarPorId(@PathVariable Long pessoaId, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaEncontrada = pessoaService.atualizar(pessoaId, pessoa);
		if (pessoaEncontrada == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pessoaEncontrada);
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletarPessoaPorId(@RequestParam(name = "deleteId") Long deleteId) {
		Pessoa pessoa = pessoaService.buscarPorId(deleteId);
		if (pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		pessoaService.deletar(deleteId);
		return ResponseEntity.noContent().build();
	}

}
