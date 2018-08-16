package com.notinha.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.notinha.entidades.Cliente;
import com.notinha.entidades.ItemNotinha;
import com.notinha.entidades.Notinha;
import com.notinha.entidades.Produto;
import com.notinha.entidades.Status;
import com.notinha.filtros.FiltroNotinha;
import com.notinha.repository.NotinhaRepository;
import com.notinha.service.ClienteService;
import com.notinha.service.NotinhaService;
import com.notinha.service.ProdutoService;
import com.notinha.util.Relatorios;

@Named
@ViewScoped
public class NotinhaController {

	private Notinha notinhaBean;
	private List<Notinha> notinhas;
	private List<Cliente> clientes;
	private Cliente cliente;
	private ItemNotinha itemNotinha;
	private Produto produto;
	private Boolean clienteNaoSelecionado = true;
	private FiltroNotinha filtroNotinha;
	private Status sts;
	
	
	
//	private String notinhaString = "";

	private List<ItemNotinha> ItemNotinhas;
	
	private String txtIdCliente = "";
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private NotinhaService notinhaService;
	@Autowired
	private NotinhaRepository notinhaRepository;
	
	@PostConstruct
	public void init() {
		this.clienteNaoSelecionado = true;
		this.notinhaBean = new Notinha();
		this.notinhaBean.setValorNotinha(0.0);
		this.cliente = new Cliente();
		this.itemNotinha = new ItemNotinha();
		this.produto = new Produto();
		this.ItemNotinhas = new ArrayList<>();
		this.txtIdCliente = "";
		this.notinhas = new ArrayList<>();
		this.filtroNotinha = new FiltroNotinha();
		this.clientes = new ArrayList<>();
		listClientes();
	}

	public void salvar(){
		
		this.notinhaBean.setItemNotinha(this.ItemNotinhas);
		
		if(this.notinhaBean.getItemNotinha().size()>0){
			this.notinhaBean = notinhaService.salvar(this.notinhaBean, this.cliente);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgImpressaoNota').show();");
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Lista de Itens vazia ou Cliente não Selecionado"));
		}
		
		
	}
	
	public void listarNotinhaAberta() {
		
		this.notinhas = this.notinhaService.listarNotinhaStatus(Status.ABERTA);
		
	}
	
	public void listClientes() {
		this.clientes = clienteService.listarTodos();
	}
	
	public String carregaNotinhas() {
		listarNotinhaAberta();
		return "/Notinha/notinha/notinhas.xhtml";
	}
	
	
	public void lerProduto(){
		
		salver(new com.notinha.util.lerProduto().lerprodutos());
	}
	
	public void salver(List<Produto> lp) {
		for (Produto produto : lp) {
			produtoService.salvar(produto);
		}
	}
	
//	public void adicionarItem(){
//		this.itemNotinha.setProduto(this.produto);
//		this.itemNotinha.setValorTotal(this.itemNotinha.getValorItem()*this.itemNotinha.getQuantidade());
//		
//		this.ItemNotinhas.add(itemNotinha);
//		
//		for (ItemNotinha itemNotinha : ItemNotinhas) {
//			this.notinhaBean.setValorNotinha(this.notinhaBean.getValorNotinha()+itemNotinha.getValorTotal());
//		}
//		
//		this.itemNotinha = new ItemNotinha();
//		this.produto = new Produto();
//	}
//	
//	
	public void lancarItem(){
		this.itemNotinha.setProduto(this.produto);
		this.itemNotinha.setValorTotal(this.itemNotinha.getValorItem()*this.itemNotinha.getQuantidade());
		this.ItemNotinhas.add(itemNotinha);
		atualizaValorNotinha();
		this.produto = new Produto();
		this.itemNotinha = new ItemNotinha();
	}
	
//	metodos de busca
	
	public void clienteById(){
		if(this.txtIdCliente.equals("r") || this.txtIdCliente.equals("R") ){
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgPesquisaCliente').show();");
		
		}else {
			Cliente clienteTmp = new Cliente();
			clienteTmp.setId(Integer.parseInt(txtIdCliente));
			this.cliente = clienteService.buscaById(clienteTmp);
			
			
		}
	}
	
	public void produtoByCodigo(){
		this.produto = produtoService.produtoByCodigo(this.produto);
		this.itemNotinha.setQuantidade(1);
		this.itemNotinha.setValorItem(this.produto.getValor());
		System.out.println("produto ASDD");
	}
	
	
	
	private void atualizaValorNotinha(){
		this.notinhaBean.setValorNotinha(0.0);
		if (ItemNotinhas.size()>0){
			for (ItemNotinha item : ItemNotinhas) {
				this.notinhaBean.setValorNotinha(this.notinhaBean.getValorNotinha()+item.getValorTotal());
			}
		}
	}
	
	public void geraNotinha() throws IOException {
		Notinha nota = new Notinha();
		nota = notinhaRepository.findOne(this.notinhaBean.getId());
		List<Notinha> listNotinha = new ArrayList<>();
		listNotinha.add(nota);
		
		Relatorios rel = new Relatorios();
		
		rel.imprimeRelatorio(listNotinha, "notinha.jasper");
	}
	
//	Getters e Setters-------------------------------------------	
	public Notinha getNotinhaBean() {
		return notinhaBean;
	}


	public void setNotinhaBean(Notinha notinhaBean) {
		this.notinhaBean = notinhaBean;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public ItemNotinha getItemNotinha() {
		return itemNotinha;
	}


	public void setItemNotinha(ItemNotinha itemNotinha) {
		this.itemNotinha = itemNotinha;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<ItemNotinha> getItemNotinhas() {
		return ItemNotinhas;
	}


	public void setItemNotinhas(List<ItemNotinha> itemNotinhas) {
		ItemNotinhas = itemNotinhas;
	}

	public String getTxtIdCliente() {
		return txtIdCliente;
	}

	public void setTxtIdCliente(String txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
	}

	public Boolean getClienteNaoSelecionado() {
		return clienteNaoSelecionado;
	}

	public void setClienteNaoSelecionado(Boolean clienteNaoSelecionado) {
		this.clienteNaoSelecionado = clienteNaoSelecionado;
	}

	public List<Notinha> getNotinhas() {
		return notinhas;
	}

	public void setNotinhas(List<Notinha> notinhas) {
		this.notinhas = notinhas;
	}

	public FiltroNotinha getFiltroNotinha() {
		return filtroNotinha;
	}

	public void setFiltroNotinha(FiltroNotinha filtroNotinha) {
		this.filtroNotinha = filtroNotinha;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Status getSts() {
		return sts;
	}

	public void setSts(Status sts) {
		this.sts = sts;
	}

	
	
	
}
