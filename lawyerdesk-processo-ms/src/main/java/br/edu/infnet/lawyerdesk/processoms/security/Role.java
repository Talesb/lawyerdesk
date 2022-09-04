package br.edu.infnet.lawyerdesk.processoms.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	private String descricao;

	public Role() {
	}

	public Role(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String getAuthority() {
		return this.descricao;
	}

}
