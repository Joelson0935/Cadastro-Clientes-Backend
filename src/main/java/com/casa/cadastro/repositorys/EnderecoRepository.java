package com.casa.cadastro.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casa.cadastro.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("select e from Endereco e where e.cep = :cep")
	Endereco findByCep(String cep);
	
}
