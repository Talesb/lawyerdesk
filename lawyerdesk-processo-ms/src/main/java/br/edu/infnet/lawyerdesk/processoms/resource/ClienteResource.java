package br.edu.infnet.lawyerdesk.processoms.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lawyerdesk.processoms.dto.ClienteDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Cliente;
import br.edu.infnet.lawyerdesk.processoms.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Cliente> getAll() {
		return this.clienteService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<Cliente> getById(@PathVariable Long id) {
		return this.clienteService.getById(id);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void save(@RequestBody ClienteDTO dto) {
		this.clienteService.save(dto);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public void update(@RequestBody ClienteDTO dto) {
		this.clienteService.save(dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteById(@PathVariable Long id) {
		this.clienteService.delete(id);
	}

}
