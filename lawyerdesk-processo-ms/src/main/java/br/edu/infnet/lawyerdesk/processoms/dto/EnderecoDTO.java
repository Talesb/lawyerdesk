package br.edu.infnet.lawyerdesk.processoms.dto;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String rua;
	private String numero;
	private String cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
