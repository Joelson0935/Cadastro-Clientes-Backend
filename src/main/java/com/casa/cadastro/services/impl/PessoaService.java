package com.casa.cadastro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casa.cadastro.models.Endereco;
import com.casa.cadastro.models.Pessoa;
import com.casa.cadastro.repositorys.PessoaRepository;
import com.casa.cadastro.services.Servico;
import com.casa.cadastro.services.exception.ObjetoNaoEncontrado;

@Service
public class PessoaService implements Servico<Pessoa> {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa salvar(Pessoa pessoa) {
		Endereco endereco = new Endereco(pessoa.getEndereco().getCep(), pessoa.getEndereco().getLogradouro(),
				pessoa.getEndereco().getComplemento(), pessoa.getEndereco().getBairro(),
				pessoa.getEndereco().getLocalidade(), pessoa.getEndereco().getUf(), pessoa.getEndereco().getIbge(),
				pessoa.getEndereco().getGia(), pessoa.getEndereco().getDdd(), pessoa.getEndereco().getSiafi());

		Pessoa novaPessoa = new Pessoa(pessoa.getId(), pessoa.getNome(), pessoa.getSobrenome(), pessoa.getIdade(),
				pessoa.getSexo(), pessoa.getTelefone(), endereco);

		pessoa = pessoaRepository.save(novaPessoa);
		return pessoa;
	}

	@Override
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		var novaPessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado("Pessoa não encontrada"));
		if (pessoa.getEndereco() == null) {
			pessoa.setEndereco(novaPessoa.getEndereco());
		}
		pessoa.setId(id);
		novaPessoa = pessoa;
		novaPessoa = pessoaRepository.save(pessoa);
		return pessoa;
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		Pessoa pessoaEncontrada = pessoaRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontrado(id + " Não encontrado."));
		return pessoaEncontrada;
	}

	@Override
	public List<Pessoa> buscarListaCompleta() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return pessoas;
	}

	public Page<Pessoa> buscarListaPaginada(Pageable pageable) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);
		return pessoas;
	}

	@Override
	public void deletar(Long id) {
		pessoaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado(id + " não foi encontrado"));
		pessoaRepository.deleteById(id);
	}

}
