package br.edu.infnet.lawyerdesk.processoms.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdvResource {

	@GetMapping(value = "/adv")
	@PreAuthorize("hasRole('ADV')")
	public String helloWorld(SecurityContextHolderAwareRequestWrapper request) {
		System.out.println(request.isUserInRole("ADV"));
		return "hello world ADV";
	}
}
