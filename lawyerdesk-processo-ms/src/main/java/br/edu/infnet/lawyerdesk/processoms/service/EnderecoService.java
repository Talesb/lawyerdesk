package br.edu.infnet.lawyerdesk.processoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lawyerdesk.processoms.dto.EnderecoDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Endereco;
import br.edu.infnet.lawyerdesk.processoms.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> getAll() {
		return this.enderecoRepository.findAll();
	}

	public Optional<Endereco> getById(Long id) {
		return this.enderecoRepository.findById(id);
	}

	public void save(Endereco endereco) {
		this.enderecoRepository.save(endereco);
	}

	public void save(EnderecoDTO dto) {

		Endereco endereco = new Endereco();

		if (dto.getId() != null) {
			endereco = this.getById(dto.getId()).orElse(new Endereco());
		}

		endereco.setCep(dto.getCep());
		endereco.setNumero(dto.getNumero());
		endereco.setRua(dto.getRua());

		this.enderecoRepository.save(endereco);
	}

	public void delete(Long id) {
		this.enderecoRepository.deleteById(id);
	}

}
