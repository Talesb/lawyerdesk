package br.edu.infnet.lawyerdesk.processoms.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public List<Cliente> getAll() {
		return this.clienteService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public Optional<Cliente> getById(@PathVariable Long id) {
		return this.clienteService.getById(id);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public ResponseEntity<Cliente> save(@RequestBody ClienteDTO dto) {
		Cliente saved = this.clienteService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void update(@RequestBody ClienteDTO dto) throws Exception {
		this.clienteService.update(dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		this.clienteService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/advogado/{advogadoid}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public List<ClienteDTO> getByIdAdvogado(@PathVariable Long advogadoid) {
		return this.clienteService.getByIdAdvogado(advogadoid);
	}


}
