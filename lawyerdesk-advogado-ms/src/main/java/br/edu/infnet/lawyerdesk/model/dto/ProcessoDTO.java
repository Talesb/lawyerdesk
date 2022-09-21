package br.edu.infnet.lawyerdesk.model.dto;

import java.io.Serializable;
import java.util.List;

public class ProcessoDTO implements Serializable {

	private Long id;
	private String numero;
	private String descricao;
	private StatusProcesso status;
	private List<Long> idsAdvogados;
	private List<Long> idsClientes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public StatusProcesso getStatus() {
		return status;
	}

	public void setStatus(StatusProcesso status) {
		this.status = status;
	}

	public List<Long> getIdsAdvogados() {
		return idsAdvogados;
	}

	public void setIdsAdvogados(List<Long> idsAdvogados) {
		this.idsAdvogados = idsAdvogados;
	}

	public List<Long> getIdsClientes() {
		return idsClientes;
	}

	public void setIdsClientes(List<Long> idsClientes) {
		this.idsClientes = idsClientes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
