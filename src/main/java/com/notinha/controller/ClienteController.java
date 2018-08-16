package com.notinha.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.notinha.entidades.Cliente;
import com.notinha.service.ClienteService;
import com.notinha.util.WebServiceCep;

@Named
@ViewScoped
public class ClienteController {

	private List<Cliente> clientes;
	private Cliente clienteBean;
	private Cliente clienteSelect;
	private Boolean isJuridica = false;
	private Boolean isFisica = true;
	private Boolean isAlterando = false;
	private String mascaraCpfCnpj = "999.999.999-99";
	private String dataString;
	private String paramBusca = "";
	private String campoBusca = "";
	private Integer idCliSelect = 156465465;
	

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	

	@Autowired
	private ClienteService clienteService;
	

	@PostConstruct
	public void init() {
		this.clienteBean = new Cliente();
		this.clienteBean.setTipoPessoa("PF");
		
		clientes = new ArrayList<>();
		if (isAlterando){
			this.clienteBean = this.clienteSelect;
			this.isAlterando = false;
		}
		clienteSelect = new Cliente();
	}

	public void salvar(ActionEvent event) {

				

		clienteService.salvar(clienteBean);
		if (!(clienteBean.getId() == null)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Cliente: " + clienteBean.getId() + " - " + clienteBean.getNomeRazao() + " Cadastrado com Sucesso"));
		}
		clienteBean = new Cliente();
	}

	public void buscaCep() {
		String cep = this.clienteBean.getCep();
		WebServiceCep wsCep = WebServiceCep.searchCep(cep);
		
		if (wsCep.wasSuccessful()) {
			this.clienteBean.setEndereco(wsCep.getLogradouroFull());
			this.clienteBean.setBairro(wsCep.getBairro());
			this.clienteBean.setCidade(wsCep.getCidade());
			this.clienteBean.setUf(wsCep.getUf());
			
		}
	}
	
	public void select(){
		System.out.println("parametro select foi:" + this.paramBusca );
		
		
	}
	
	
	public void buscarById(){
		this.clienteBean = this.clienteService.buscaById(clienteBean);
	}
	
	public void pesquisaCliente(){
		
		clientes = clienteService.pesquisaCliente(this.paramBusca, this.campoBusca);
	}

	
	
	public String limpaTelaCadastro(){
		this.clienteBean = new Cliente();
		this.clienteSelect = new Cliente();
		this.isAlterando = false;
		this.paramBusca = "";
		this.campoBusca = "";
		this.isJuridica = false;
		this.isFisica = true;
		this.mascaraCpfCnpj = "999.999.999-99";
		this.dataString = "";
		return "/cliente/cadastro-cliente.jsf";
	}
	
	public void trocarPjPf() {
		if (clienteBean.getTipoPessoa().equals("PF")) {
			this.isFisica = true;
			this.isJuridica = false;
			this.mascaraCpfCnpj = "999.999.999-99";
		}
		if (clienteBean.getTipoPessoa().equals("PJ")) {
			this.isFisica = false;
			this.isJuridica = true;
			this.mascaraCpfCnpj = "99.999.999/9999-99";

		}

	}
	
	public String carregaAlteracao(){
		this.clienteSelect.setId(idCliSelect);
		this.clienteBean= clienteService.buscaById(clienteSelect); 
		
		this.isAlterando = true;
		return "/cliente/cadastro-cliente.jsf";
	}
	
//	Getters e Setters-------------------------------------------
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(Cliente clienteBean) {
		this.clienteBean = clienteBean;
	}

	public Cliente getClienteSelect() {
		return clienteSelect;
	}

	public void setClienteSelect(Cliente clienteSelect) {
		this.clienteSelect = clienteSelect;
	}

	public Boolean getIsJuridica() {
		return isJuridica;
	}

	public void setIsJuridica(Boolean isJuridica) {
		this.isJuridica = isJuridica;
	}

	public Boolean getIsFisica() {
		return isFisica;
	}

	public void setIsFisica(Boolean isFisica) {
		this.isFisica = isFisica;
	}

	public Boolean getIsAlterando() {
		return isAlterando;
	}

	public void setIsAlterando(Boolean isAlterando) {
		this.isAlterando = isAlterando;
	}

	public String getMascaraCpfCnpj() {
		return mascaraCpfCnpj;
	}

	public void setMascaraCpfCnpj(String mascaraCpfCnpj) {
		this.mascaraCpfCnpj = mascaraCpfCnpj;
	}

	public String getDataString() {
		return dataString;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
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

	public SimpleDateFormat getFormato() {
		return formato;
	}

	public void setFormato(SimpleDateFormat formato) {
		this.formato = formato;
	}

	public Integer getIdCliSelect() {
		return idCliSelect;
	}

	public void setIdCliSelect(Integer idCliSelect) {
		this.idCliSelect = idCliSelect;
	}
	
	
	
	
	
	
	
	
}
