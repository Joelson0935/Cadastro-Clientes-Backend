package com.casa.cadastro.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Servico<E> {

	E salvar(E obj);

	E atualizar(Long id, E obj);

	E buscarPorId(Long id);

	List<E> buscarListaCompleta();

	Page<E> buscarListaPaginada(Pageable pageable);

	void deletar(Long id);
}
