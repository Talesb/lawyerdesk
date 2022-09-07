package br.edu.infnet.lawyerdesk.processoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lawyerdesk.processoms.dto.SistemaDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Sistema;
import br.edu.infnet.lawyerdesk.processoms.repository.SistemaRepository;

@Service
public class SistemaService {

	@Autowired
	private SistemaRepository sistemaRepository;

	public List<Sistema> getAll() {
		return this.sistemaRepository.findAll();
	}

	public Optional<Sistema> getById(Long id) {
		return this.sistemaRepository.findById(id);
	}

	public void save(Sistema sistema) {
		this.sistemaRepository.save(sistema);
	}

	public void save(SistemaDTO dto) {

		Sistema sistema = new Sistema();

		if (dto.getId() != null) {
			sistema = this.getById(dto.getId()).orElse(new Sistema());
		}

		sistema.setDescricao(dto.getDescricao());

		this.sistemaRepository.save(sistema);
	}

	public void delete(Long id) {
		this.sistemaRepository.deleteById(id);
	}

}
