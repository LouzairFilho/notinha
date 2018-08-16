package com.notinha.filtros;

import java.util.Date;

public class FiltroNotinha {

	private Integer idClinte;
	private Date dataInicio;
	private Date dataFim;
	private String status;
	
	
	public Integer getIdClinte() {
		return idClinte;
	}
	public void setIdClinte(Integer idClinte) {
		this.idClinte = idClinte;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
