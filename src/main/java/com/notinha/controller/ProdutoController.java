package com.notinha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.notinha.entidades.Produto;
import com.notinha.service.ProdutoService;
import com.notinha.util.lerProduto;

@Named
@ViewScoped
public class ProdutoController {

	private List<Produto> produtos;
	private List<Produto> produtosFilter;
	private Produto produtoBean;
	private Produto produtoSelect;

	private Integer idproduto = 0;
	private String paramBusca = "";
	private String campoBusca = "";
	private String campoBuscaCodigo = "";
	
	

	@Autowired
	private ProdutoService produtoService;
	

	@PostConstruct
	public void init() {
		this.produtoBean = new Produto();
		
		this.produtos = new ArrayList<>();
		this.produtosFilter = new ArrayList<>();
		produtoSelect = new Produto();
	}

	public void salvar(ActionEvent event) {

				

		produtoService.salvar(produtoBean);
		if (!(produtoBean.getId() == null)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Cliente: " + produtoBean.getCodigo() + " - " + produtoBean.getDescricao() + " Cadastrado com Sucesso"));
		}
		produtoBean = new Produto();
	}

	public void buscaByCodigo() {
		this.produtoBean = produtoService.produtoByCodigo(this.produtoBean);
	}
	
	public void pesquisaProduto(){
		if (campoBusca.length() >= 3) {
			produtos = produtoService.pesquisaProduto("descricao", this.campoBusca);
		}
	}
	
	public void pesquisaByCodigo(){
		if (campoBuscaCodigo.length() >= 3) {
			produtos = produtoService.pesquisaProduto("codigo", this.campoBuscaCodigo);
		}
	}
	
	public void excluir() {
		this.idproduto = this.produtoSelect.getId();
		produtoService.excluir(this.idproduto);
		pesquisaByCodigo();
		pesquisaProduto();
	}
	
	public void listarTodos(){
		this.produtos = produtoService.listarTodos();
	}
	
	public void select(){
		System.out.println("parametro select foi:" + this.paramBusca );
		
		
	}
	
	
	public String limpaTelaCadastro(){
		this.produtoBean = new Produto();
		this.produtoSelect = new Produto();
		this.paramBusca = "";
		this.campoBusca = "";
		
		return "/produto/cadastro-produto.jsf";
	}
	
//	Getters e Setters-------------------------------------------
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public List<Produto> getProdutosFilter() {
		return produtosFilter;
	}

	public void setProdutosFilter(List<Produto> produtosFilter) {
		this.produtosFilter = produtosFilter;
	}

	public Produto getProdutoBean() {
		return produtoBean;
	}

	public void setProdutoBean(Produto produtoBean) {
		this.produtoBean = produtoBean;
	}

	public Produto getProdutoSelect() {
		return produtoSelect;
	}

	public void setProdutoSelect(Produto produtoSelect) {
		this.produtoSelect = produtoSelect;
	}

	public String getParamBusca() {
		return paramBusca;
	}

	public void setParamBusca(String paramBusca) {
		this.paramBusca = paramBusca;
	}

	public String getCampoBusca() {
		return campoBusca;
	}

	public void setCampoBusca(String campoBusca) {
		this.campoBusca = campoBusca;
	}

	public String getCampoBuscaCodigo() {
		return campoBuscaCodigo;
	}

	public void setCampoBuscaCodigo(String campoBuscaCodigo) {
		this.campoBuscaCodigo = campoBuscaCodigo;
	}

	public Integer getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Integer idproduto) {
		this.idproduto = idproduto;
	}
	
	

	
	




}