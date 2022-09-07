package br.edu.infnet.lawyerdesk.processoms.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LWD_Sistema")
public class Sistema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	@OneToMany(mappedBy = "sistema", cascade = CascadeType.ALL)
	private Set<Processo> processos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(Set<Processo> processos) {
		this.processos = processos;
	}
 

}
