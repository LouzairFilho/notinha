package com.notinha.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull @NotBlank
	private String nomeRazao;
	
	private String nomeFantasia;
	
	@NotNull @NotBlank
	@Column(unique = true)
	private String cpfCnpj;
	
	private String tipoPessoa;
	
	private String telefone;
	
	private String celular;
	
	private String email;
	
	private String endereco;
	
	private String cep;
	
	private String bairro;
	
	private String cidade; 
	
	private String uf;
	
	private String contato;
	
	private String obs;
	
	@Transient
	private String telefones;

	@Transient
	private String cpfCnpjView;
	
	@Transient
	private Boolean isAlteracao = false;

	public String getCpfCnpjView() throws java.text.ParseException {
		if (this.tipoPessoa.equals("PF")) {
			MaskFormatter mask;
			mask = new MaskFormatter("###.###.###-##");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(this.cpfCnpj);
		}
		if (this.tipoPessoa.equals("PJ")) {
			MaskFormatter mask;
			mask = new MaskFormatter("##.###.###/####-##");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(this.cpfCnpj);
		}

		return cpfCnpjView;
	}

	public void setCpfCnpjView(String cpfCnpjView) {
		this.cpfCnpjView = cpfCnpjView;
	}
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getTelefones() {
		this.telefones = "";
		if (!this.telefone.isEmpty()){
			this.telefones = this.telefone;
		}if(!this.telefones.isEmpty() && !this.celular.isEmpty()){
			this.telefones = this.telefones +" - " + this.celular;
		}
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public Boolean getIsAlteracao() {
		return isAlteracao;
	}

	public void setIsAlteracao(Boolean isAlteracao) {
		this.isAlteracao = isAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao.toUpperCase();
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia.toUpperCase();
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toUpperCase();
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco.toUpperCase();
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade.toUpperCase();
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf.toUpperCase();
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato.toUpperCase();
	}

	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs.toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
