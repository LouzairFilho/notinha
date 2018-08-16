package com.notinha.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notinha.entidades.Cliente;
import com.notinha.entidades.Notinha;
import com.notinha.entidades.Status;
import com.notinha.repository.NotinhaRepository;

@Service
public class NotinhaService {

	@Autowired
	private NotinhaRepository notinhaRepository;
	
	
	public Notinha salvar(Notinha notinha, Cliente cliente) {
		
		notinha.setCliente(cliente);
		notinha.setDataNotinha(new Date());
		notinha.setStatus(Status.ABERTA);
		
		return notinhaRepository.save(notinha);
	}


	public List<Notinha> listarNotinhaStatus(Status status) {
		
		return notinhaRepository.listarNotinhaAberta(status);
	}
	
	
}
