package br.com.ECommerce.util;

import java.util.Date;

public class SemanaMes {

	private Integer semana;
	private Date dataInicio;
	private Date dataFim;

	public SemanaMes() {
	}

	public SemanaMes(Integer semana, Date dataInicio, Date dataFim) {
		this.semana = semana;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Integer getSemana() {
		return semana;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}