package br.edu.infnet.lawyerdesk.processoms.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWord {

	@GetMapping(value = "/hello")
	public String helloWorld() {
		return "hello";
	}

}
