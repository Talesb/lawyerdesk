package br.edu.infnet.lawyerdesk.processoms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lawyerdesk.processoms.dto.ClienteDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Cliente;
import br.edu.infnet.lawyerdesk.processoms.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> getAll() {
		return this.clienteRepository.findAll();
	}

	public Optional<Cliente> getById(Long id) {
		return this.clienteRepository.findById(id);
	}

	public void save(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}

	public void save(ClienteDTO dto) {

		Cliente cliente = new Cliente();

		if (dto.getId() != null) {
			cliente = this.getById(dto.getId()).orElse(new Cliente());
		}

		cliente.setCpf(dto.getCpf());
		cliente.setNome(dto.getNome());
		cliente.setTelefone(dto.getTelefone());

		this.clienteRepository.save(cliente);
	}

	public void delete(Long id) {
		this.clienteRepository.deleteById(id);
	}

}
