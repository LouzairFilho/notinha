package com.notinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.notinha.entidades.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("select c from Cliente c where c.cpfCnpj = :cpfcnpj")
	Cliente buscaByCpfCNpj(@Param("cpfcnpj") String cpfcnpj);
	
	@Query("select c from Cliente c where c.cpfCnpj like :cpfcnpj%")
	List<Cliente> listarByCpfCNpj(@Param("cpfcnpj") String cpfcnpj);
			
	@Query("select c from Cliente c where c.id = :id")
	List<Cliente> listarById(@Param("id") Integer id);
	
	@Query("select c from Cliente c where LOWER(c.nomeRazao) like LOWER(:nomeRazao)")
	List<Cliente> listarByNomeRazao(@Param("nomeRazao") String nomeRazao);
	
	@Query("select c from Cliente c where LOWER(c.nomeFantasia) like LOWER(:nomeFantasia)")
	List<Cliente> listarByFantasia(@Param("nomeFantasia") String nomeFantasia);
	

}
