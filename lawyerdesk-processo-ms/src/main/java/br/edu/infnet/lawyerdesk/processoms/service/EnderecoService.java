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

	public Endereco save(EnderecoDTO dto) {

		Endereco endereco = new Endereco();
		endereco.setCep(dto.getCep());
		endereco.setNumero(dto.getNumero());
		endereco.setRua(dto.getRua());

		return this.enderecoRepository.save(endereco);
	}
	
	public Endereco update(EnderecoDTO dto) throws Exception {

		Endereco endereco = this.getById(dto.getId()).orElseThrow(()->{
			return new Exception("Nenhum endereco encontrado com o id informado");
		});
		endereco.setCep(dto.getCep());
		endereco.setNumero(dto.getNumero());
		endereco.setRua(dto.getRua());

		return this.enderecoRepository.save(endereco);
	}
	

	public void delete(Long id) {
		this.enderecoRepository.deleteById(id);
	}

}
