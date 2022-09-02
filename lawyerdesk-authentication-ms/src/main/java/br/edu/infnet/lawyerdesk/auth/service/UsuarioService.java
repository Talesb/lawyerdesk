package br.edu.infnet.lawyerdesk.auth.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.edu.infnet.lawyerdesk.auth.model.Usuario;
import br.edu.infnet.lawyerdesk.auth.security.PasswordEncoder;

@RequestScoped
public class UsuarioService {

	@Inject
	PasswordEncoder passwordEncoder;

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		return Usuario.listAll();
	}

	@Transactional
	public void persist(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		Usuario.persist(usuario);

	}

	@Transactional
	public Usuario findById(Long id) {
		return Usuario.findById(id);
	}

	@Transactional
	public Usuario update(Long id, Usuario usuarioParam) {
		Usuario usuario = Usuario.findById(id);
		usuario.setLogin(usuarioParam.getLogin());
		usuario.setSenha(passwordEncoder.encode(usuarioParam.getSenha()));
		System.out.println(usuario.getLogin());
		Usuario.persist(usuario);
		Usuario.flush();
		return usuario;
	}

	@Transactional
	public void deleteById(Long id) {
		Usuario.deleteById(id);
	}

	@Transactional
	public Usuario findByLogin(String login) {
		return Usuario.findByLogin(login);
	}

}
