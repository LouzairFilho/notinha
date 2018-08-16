package com.notinha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notinha.entidades.Cliente;
import com.notinha.entidades.Produto;
import com.notinha.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvar(Produto produto) {
		
		
		return produtoRepository.save(produto);
	}
	
	public List<Produto> listarTodos(){
		
		return produtoRepository.findAll();
	}
	
	
	
	public List<Produto> pesquisaProduto(String paramBusca, String campoBusca){
		List<Produto> produtos = new ArrayList<>();
		
		if (paramBusca.equals("id")){
			
			produtos.add(produtoRepository.findOne(Integer.parseInt(campoBusca)));
		}
		if (paramBusca.equals("descricao")) {

			return produtoRepository.listarByDescricao("%" + campoBusca + "%");
			
		}
		
		if (paramBusca.equals("codigo")) {

			return produtoRepository.listarByCodigo("%" + campoBusca + "%");
			
		}
		return produtos;
	}

	public Produto produtoByCodigo(Produto produto) {
		Produto produtoBuscado = new Produto();

		List<Produto> listProduto = new ArrayList<Produto>();

		listProduto = produtoRepository.listarByCodigo(produto.getCodigo().toUpperCase());
		if (listProduto.size() > 0) {
			produtoBuscado = listProduto.get(0);
			return produtoBuscado;
		}
		return produto;
	}

	public void excluir(Integer id) {
		
		if (produtoRepository.exists(id)){
			produtoRepository.delete(id);
		}
		
	}
	
	
}
