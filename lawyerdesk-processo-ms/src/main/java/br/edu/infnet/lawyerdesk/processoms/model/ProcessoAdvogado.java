package br.edu.infnet.lawyerdesk.processoms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LWD_Processo_Advogado")
public class ProcessoAdvogado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique=true,nullable=false)
	private int advogadoId;

	@ManyToOne
	@JoinColumn(name = "processoId")
	private Processo processo;

	public int getAdvogadoId() {
		return advogadoId;
	}

	public void setAdvogadoId(int advogadoId) {
		this.advogadoId = advogadoId;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
