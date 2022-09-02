package br.edu.infnet.lawyerdesk.auth.loader;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.edu.infnet.lawyerdesk.auth.model.Role;
import br.edu.infnet.lawyerdesk.auth.model.Usuario;
import br.edu.infnet.lawyerdesk.auth.service.RoleService;
import br.edu.infnet.lawyerdesk.auth.service.UsuarioService;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class LawyerDeskAuthLoader {

	@Inject
	UsuarioService usuarioService;
	
	@Inject
	RoleService roleService;

	private static final Logger LOGGER = Logger.getLogger("AdvogadoLoader");

	@Transactional
	void onStart(@Observes StartupEvent ev) {
	
	Usuario usuario = new Usuario();
	usuario.setLogin("talesb_adv");
	usuario.setSenha("1234");
	
	Usuario usuarioAdmin = new Usuario();
	usuarioAdmin.setLogin("talesb_adv_admin");
	usuarioAdmin.setSenha("12345");
	
	Role adv = new Role();
	adv.setDescricao("ADV");

	Role fullAccessAdmin = new Role();
	fullAccessAdmin.setDescricao("ADMIN");
	
	roleService.persist(fullAccessAdmin);
	roleService.persist(adv);
	
	usuarioAdmin.setRoles(Set.of(fullAccessAdmin));
	usuario.setRoles(Set.of(adv));
	
	usuarioService.persist(usuarioAdmin);
	usuarioService.persist(usuario);
	
	}
}
