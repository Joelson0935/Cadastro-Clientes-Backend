package com.casa.cadastro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.cadastro.models.Endereco;
import com.casa.cadastro.repositorys.EnderecoRepository;
import com.casa.cadastro.services.Servico;

@Service
public class EnderecoService implements Servico<Endereco> {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public Endereco salvar(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

	@Override
	public Endereco atualizar(Long id, Endereco endereco) {
		Endereco enderecoEncontrado = enderecoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		endereco.setId(id);
		enderecoEncontrado = endereco;
		enderecoEncontrado = enderecoRepository.save(enderecoEncontrado);
		return enderecoEncontrado;
	}

	@Override
	public Endereco buscarPorId(Long id) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return endereco;
	}

	@Override
	public List<Endereco> buscarListaCompleta() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		return enderecos;
	}

	@Override
	public Page<Endereco> buscarListaPaginada(Pageable pageable) {
		Page<Endereco> enderecos = enderecoRepository.findAll(pageable);
		return enderecos;
	}

	@Override
	public void deletar(Long id) {
		enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		enderecoRepository.deleteById(id);
	}

}
