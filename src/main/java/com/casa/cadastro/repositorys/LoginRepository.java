package com.casa.cadastro.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.casa.cadastro.models.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	@Query("select l from Login l where l.email = :email")
	Login buscarPorEmail(String email);
}
