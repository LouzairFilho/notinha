package com.notinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notinha.entidades.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Query("select p from Produto p where LOWER(p.descricao) like LOWER(:descricao)")
	List<Produto> listarByDescricao(@Param("descricao") String descricao);

	@Query("select p from Produto p where LOWER(p.codigo) like LOWER(:codigo)")
	List<Produto> listarByCodigo(@Param("codigo") String codigo);
	
	@Query("select p from Produto p where p.codigo = :codigo")
	List<Produto> buscarByCodigo(@Param("codigo") String codigo);
}
