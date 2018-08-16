package com.notinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notinha.entidades.Notinha;
import com.notinha.entidades.Status;

public interface NotinhaRepository extends JpaRepository<Notinha, Integer> {

	@Query("select n from Notinha n where n.status = :status")
	List<Notinha> listarNotinhaAberta(@Param("status") Status aberta);

	
}
