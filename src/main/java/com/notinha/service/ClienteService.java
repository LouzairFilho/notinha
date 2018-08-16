package com.notinha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notinha.entidades.Cliente;
import com.notinha.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		cliente.setCpfCnpj(removeMascaraCpfCnpf(cliente));
		return clienteRepository.save(cliente);
	}

	public List<Cliente> listarTodos() {

		return clienteRepository.findAll();
	}

	public Cliente buscaById(Cliente cliente) {

		Cliente clienteBuscado = new Cliente();

		List<Cliente> listCliente = new ArrayList<Cliente>();

		listCliente = clienteRepository.listarById(cliente.getId());
		if (listCliente.size() > 0) {
			clienteBuscado = listCliente.get(0);
			clienteBuscado.setIsAlteracao(true);
			return clienteBuscado;
		}
		return cliente;
	}

	public List<Cliente> pesquisaCliente(String paramBusca, String campoBusca) {
		List<Cliente> listCliente = new ArrayList<>();
		if (paramBusca.equals("id")) {
			return clienteRepository.listarById(Integer.parseInt(campoBusca));
		} else {
			if (campoBusca.length() >= 3) {
				if (paramBusca.equals("cpfCnpj")) {

					return clienteRepository.listarByCpfCNpj("%" + campoBusca + "%");
				}
				if (paramBusca.equals("nomeFantasia")) {

					return clienteRepository.listarByFantasia("%" + campoBusca + "%");
				}
				if (paramBusca.equals("nomeRazao")) {

					return clienteRepository.listarByNomeRazao("%" + campoBusca + "%");
				}

			}
		}

		return listCliente;
	}

	private String removeMascaraCpfCnpf(Cliente cliente){
		if(cliente.getTipoPessoa().equals("PJ")){
			cliente.setCpfCnpj(cliente.getCpfCnpj().replace("/", ""));
			cliente.setCpfCnpj(cliente.getCpfCnpj().replace("-", ""));
			cliente.setCpfCnpj(cliente.getCpfCnpj().replace(".", ""));
		}
		if(cliente.getTipoPessoa().equals("PF")){
			cliente.setCpfCnpj(cliente.getCpfCnpj().replace("-", ""));
			cliente.setCpfCnpj(cliente.getCpfCnpj().replace(".", ""));
		}
		return cliente.getCpfCnpj();
	}

}
