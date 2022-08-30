package br.edu.infnet.lawyerdesk.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.edu.infnet.lawyerdesk.model.Advogado;
import br.edu.infnet.lawyerdesk.repository.AdvogadoRepository;

@RequestScoped
public class AdvogadoService {

	@Inject
	private AdvogadoRepository advogadoRepository;

	public List<Advogado> findAll() {
		return (List<Advogado>) this.advogadoRepository.findAll();
	}

	public void persist(Advogado advogado) {
		this.advogadoRepository.save(advogado);
	}

	public Advogado findById(Long id) {
		return this.advogadoRepository.findById(id).orElse(null);
	}

	public Advogado update(Long id, Advogado advogadoParam) {
		Advogado adv = this.advogadoRepository.findById(id).orElse(null);
		adv.setNome(advogadoParam.getNome());
		adv.setCpf(advogadoParam.getCpf());
		adv.setOab(advogadoParam.getOab());
		adv.setEspecialidades(advogadoParam.getEspecialidades());
		this.advogadoRepository.save(adv);
		return adv;
	}

	public void deleteById(Long id) {
		this.advogadoRepository.deleteById(id);
	}

}
