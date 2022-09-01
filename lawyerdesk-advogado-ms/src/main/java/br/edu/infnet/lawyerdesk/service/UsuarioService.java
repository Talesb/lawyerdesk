package br.edu.infnet.lawyerdesk.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.edu.infnet.lawyerdesk.model.Advogado;
import br.edu.infnet.lawyerdesk.model.Usuario;
import br.edu.infnet.lawyerdesk.repository.UsuarioRepository;
import br.edu.infnet.lawyerdesk.security.PasswordEncoder;

@RequestScoped
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;
	
	@Inject
	PasswordEncoder passwordEncoder;

	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public void persist(Usuario usuario) {
		this.usuarioRepository.save(usuario);

	}

	public Usuario findById(Long id) {
		return this.usuarioRepository.findById(id).orElse(null);
	}

	public Usuario update(Long id, Usuario usuarioParam) {
		Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
		usuario.setLogin(usuarioParam.getLogin());
		usuario.setSenha(passwordEncoder.encode(usuarioParam.getSenha()));
		this.usuarioRepository.save(usuario);
		return usuario;
	}

	public void deleteById(Long id) {
		System.out.println(id);
		this.usuarioRepository.deleteById(id);
	}
	
	public Usuario findByLogin(String login) {
		return this.usuarioRepository.findByLogin(login).orElse(null);
	}

}
