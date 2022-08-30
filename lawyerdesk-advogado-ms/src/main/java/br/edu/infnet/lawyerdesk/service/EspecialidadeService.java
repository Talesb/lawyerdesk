package br.edu.infnet.lawyerdesk.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.edu.infnet.lawyerdesk.model.Especialidade;
import br.edu.infnet.lawyerdesk.repository.EspecialidadeRepository;

@RequestScoped
public class EspecialidadeService {

	@Inject
	private EspecialidadeRepository especialidadeRepository;

	public List<Especialidade> findAll() {
		return (List<Especialidade>) this.especialidadeRepository.findAll();
	}

	public void persist(Especialidade especialidade) {
		this.especialidadeRepository.save(especialidade);

	}

	public Especialidade findById(Long id) {
		return this.especialidadeRepository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		this.especialidadeRepository.deleteById(id);
	}

}
