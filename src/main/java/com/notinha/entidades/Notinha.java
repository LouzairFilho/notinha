package com.notinha.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Notinha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Cliente cliente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ItemNotinha> itemNotinha;

	private Double valorNotinha;

	@Temporal(TemporalType.DATE)
	private Date dataNotinha;

	private Double desconto;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Transient
	private Integer quantItens;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

	public List<ItemNotinha> getItemNotinha() {
		return itemNotinha;
	}

	public void setItemNotinha(List<ItemNotinha> itemNotinha) {
		this.itemNotinha = itemNotinha;
	}

	public Double getValorNotinha() {
		return valorNotinha;
	}

	public void setValorNotinha(Double valorNotinha) {
		this.valorNotinha = valorNotinha;
	}

	public Date getDataNotinha() {
		return dataNotinha;
	}

	public void setDataNotinha(Date dataNotinha) {
		this.dataNotinha = dataNotinha;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public Integer getQuantItens() {
		this.quantItens = 0;
		for (ItemNotinha items : itemNotinha) {
			this.quantItens = this.quantItens + items.getQuantidade();
		}
		
		return quantItens;
	}

	public void setQuantItens(Integer quantItens) {
		this.quantItens = quantItens;
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
		Notinha other = (Notinha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
