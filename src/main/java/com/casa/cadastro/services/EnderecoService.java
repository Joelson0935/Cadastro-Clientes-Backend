package com.casa.cadastro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.cadastro.models.Endereco;
import com.casa.cadastro.repositorys.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco salvar(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

	public Endereco atualizar(Endereco endereco, Long id) {
		Endereco e = buscarPorId(id);
		if (e != null) {
			endereco = enderecoRepository.save(endereco);
			return endereco;
		}
		return null;
	}

	public Endereco buscarPorId(Long id) {
		Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return endereco;
	}

	public List<Endereco> buscarListaCompleta() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		return enderecos;
	}

	public Page<Endereco> buscarListaPaginada(Pageable pageable) {
		Page<Endereco> enderecos = enderecoRepository.findAll(pageable);
		return enderecos;
	}

	public void deletar(Long id) {
		enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		enderecoRepository.deleteById(id);
	}

	
}
