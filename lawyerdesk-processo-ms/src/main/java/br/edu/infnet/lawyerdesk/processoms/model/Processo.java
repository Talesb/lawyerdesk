package br.edu.infnet.lawyerdesk.processoms.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.infnet.lawyerdesk.processoms.dto.ProcessoDTO;

@Entity
@Table(name = "LWD_Processo")
public class Processo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;

	private String descricao;
	
	private StatusProcesso status;

	@JsonIgnore
	@OneToMany(mappedBy = "processo", cascade = CascadeType.ALL)
	private Set<ProcessoAdvogado> advogados;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "LWD_ProcessoCliente", joinColumns = { @JoinColumn(name = "processo_id") }, inverseJoinColumns = {
			@JoinColumn(name = "cliente_id") })
	private Set<Cliente> clientes;

	@JsonIgnore
	@ManyToOne
	private Sistema sistema;

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

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public void addAdvogadosAoProcesso(ProcessoAdvogado advogado) {
		if (this.getAdvogados() == null) {
			this.advogados = new HashSet<>();
		}
		this.advogados.add(advogado);

	}
	
	
	public ProcessoDTO toDTO() {
		
		
		ProcessoDTO dto = new ProcessoDTO();
		dto.setId(this.id);
		dto.setNumero(this.numero);
		dto.setStatus(this.status);
		dto.setDescricao(this.descricao);
		
		if(this.advogados!=null) {
			dto.setIdsAdvogados(this.advogados.stream().map(adv->adv.getAdvogadoId()).collect(Collectors.toList()));
		}
		
		if(this.clientes!=null) {
			dto.setIdsClientes(this.clientes.stream().map(cli->cli.getId()).collect(Collectors.toList()));
		}
		
		return dto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
