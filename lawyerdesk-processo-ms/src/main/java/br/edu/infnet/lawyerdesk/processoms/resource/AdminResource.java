package br.edu.infnet.lawyerdesk.processoms.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminResource {

	@GetMapping(value = "/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String helloWorld() {
		return "hello world admin";
	}

}
