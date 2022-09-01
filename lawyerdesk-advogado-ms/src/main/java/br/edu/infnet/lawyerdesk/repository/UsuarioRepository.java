package br.edu.infnet.lawyerdesk.repository;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;

import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.lawyerdesk.model.Usuario;

@RequestScoped
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Optional<Usuario> findByLogin(String login);

}
