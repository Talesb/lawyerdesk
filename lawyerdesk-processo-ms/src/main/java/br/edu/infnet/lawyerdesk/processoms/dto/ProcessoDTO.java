package br.edu.infnet.lawyerdesk.processoms.dto;

import java.io.Serializable;
import java.util.List;

import br.edu.infnet.lawyerdesk.processoms.model.StatusProcesso;

public class ProcessoDTO implements Serializable {

	private Long id;
	private String numero;
	private StatusProcesso status;
	private List<Integer> idsAdvogados;

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

	public List<Integer> getIdsAdvogados() {
		return idsAdvogados;
	}

	public void setIdsAdvogados(List<Integer> idsAdvogados) {
		this.idsAdvogados = idsAdvogados;
	}

}
