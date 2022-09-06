package br.edu.infnet.lawyerdesk.model.dto;

import java.io.Serializable;

public class CadastroProcessoDTO implements Serializable {

	private String oab;
	private String numeroProcesso;

	public String getOab() {
		return oab;
	}

	public void setOab(String oab) {
		this.oab = oab;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

}
