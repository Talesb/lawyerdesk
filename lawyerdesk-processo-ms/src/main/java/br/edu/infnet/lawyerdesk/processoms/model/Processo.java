package br.edu.infnet.lawyerdesk.processoms.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LWD_Processo")
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	private StatusProcesso status;

	@JsonIgnore
	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
	private Set<ProcessoAdvogado> advogados;

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

	public Set<ProcessoAdvogado> getAdvogados() {
		return advogados;
	}

	public void setAdvogados(Set<ProcessoAdvogado> advogados) {
		this.advogados = advogados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
