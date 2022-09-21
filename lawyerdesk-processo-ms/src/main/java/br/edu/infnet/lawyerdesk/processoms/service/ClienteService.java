package br.edu.infnet.lawyerdesk.processoms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lawyerdesk.processoms.dto.ClienteDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Cliente;
import br.edu.infnet.lawyerdesk.processoms.model.Processo;
import br.edu.infnet.lawyerdesk.processoms.repository.ClienteRepository;
import br.edu.infnet.lawyerdesk.processoms.repository.ProcessoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProcessoRepository processoRepository;

	public List<Cliente> getAll() {
		return this.clienteRepository.findAll();
	}

	public Optional<Cliente> getById(Long id) {
		return this.clienteRepository.findById(id);
	}

	public void save(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}

	public Cliente save(ClienteDTO dto) {

		Cliente cliente = new Cliente();
		cliente.setCpf(dto.getCpf());
		cliente.setNome(dto.getNome());
		cliente.setTelefone(dto.getTelefone());

		return this.clienteRepository.save(cliente);
	}
	
	public Cliente update(ClienteDTO dto) throws Exception {

		Cliente cliente = this.getById(dto.getId()).orElseThrow(()->{
			return new Exception("Nenhum cliente encontrado com o id informado");
		});

		cliente.setCpf(dto.getCpf());
		cliente.setNome(dto.getNome());
		cliente.setTelefone(dto.getTelefone());

		return this.clienteRepository.save(cliente);
	}
	

	public void delete(Long id) {
		this.clienteRepository.deleteById(id);
	}

	public List<ClienteDTO> getByIdAdvogado(Long advogadoid) {
		List<Cliente> clientes = this.clienteRepository.findByAdv(advogadoid);
		return clientes.stream().map(cliente->cliente.toDTO()).collect(Collectors.toList());
	}

}
