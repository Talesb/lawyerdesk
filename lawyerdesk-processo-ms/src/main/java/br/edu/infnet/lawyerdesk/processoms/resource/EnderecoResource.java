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

import br.edu.infnet.lawyerdesk.processoms.dto.EnderecoDTO;
import br.edu.infnet.lawyerdesk.processoms.model.Cliente;
import br.edu.infnet.lawyerdesk.processoms.model.Endereco;
import br.edu.infnet.lawyerdesk.processoms.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public List<Endereco> getAll() {
		return this.enderecoService.getAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public Optional<Endereco> getById(@PathVariable Long id) {
		return this.enderecoService.getById(id);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public ResponseEntity<Endereco> save(@RequestBody EnderecoDTO dto) {
		Endereco saved = this.enderecoService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public void update(@RequestBody EnderecoDTO dto) throws Exception {
		this.enderecoService.update(dto);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','ADV')")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		this.enderecoService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
