package br.edu.infnet.lawyerdesk.loader;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.edu.infnet.lawyerdesk.model.Administrador;
import br.edu.infnet.lawyerdesk.model.Advogado;
import br.edu.infnet.lawyerdesk.model.Especialidade;
import br.edu.infnet.lawyerdesk.model.Role;
import br.edu.infnet.lawyerdesk.model.Usuario;
import br.edu.infnet.lawyerdesk.repository.AdministradorRepository;
import br.edu.infnet.lawyerdesk.repository.AdvogadoRepository;
import br.edu.infnet.lawyerdesk.repository.EspecialidadeRepository;
import br.edu.infnet.lawyerdesk.repository.RoleRepository;
import br.edu.infnet.lawyerdesk.repository.UsuarioRepository;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AdvogadoLoader {

	@Inject
	private AdministradorRepository adminRepo;

	@Inject
	private AdvogadoRepository advogadoRepository;

	@Inject
	private EspecialidadeRepository especialidadeRepository;

	@Inject
	private RoleRepository roleRepository;

	@Inject
	private UsuarioRepository usuarioRepository;

	private static final Logger LOGGER = Logger.getLogger("AdvogadoLoader");

	void onStart(@Observes StartupEvent ev) {
		LOGGER.info("The application is starting...");

		Usuario usuario = new Usuario();
		usuario.setLogin("talesb_adv");
		usuario.setSenha("1234");
		
		Usuario usuarioAdmin = new Usuario();
		usuarioAdmin.setLogin("talesb_adv_admin");
		usuarioAdmin.setSenha("1234");
		
//		usuarioRepository.save(usuarioAdmin);
//		usuarioRepository.save(usuario);
		

		Role adv = new Role();
		adv.setDescricao("ADV");

		Role fullAccessAdmin = new Role();
		fullAccessAdmin.setDescricao("ADMIN");
		
		roleRepository.save(adv);
		roleRepository.save(fullAccessAdmin);
		
		usuarioAdmin.setRoles(Set.of(fullAccessAdmin));
		usuario.setRoles(Set.of(adv));
		
		Especialidade civel = new Especialidade();
		civel.setDescricao("CIVEL");

		Especialidade criminalista = new Especialidade();
		criminalista.setDescricao("CRIMINAL");

		especialidadeRepository.save(civel);
		especialidadeRepository.save(criminalista);
		
		Advogado adv1 = new Advogado();
		adv1.setCpf("24369245893");
		adv1.setNome("Tales Batista");
		adv1.setOab("268598");
		adv1.setUsuario(usuario);

		adv1.setEspecialidades(Set.of(criminalista));
		
		advogadoRepository.save(adv1);
		 
		
		Administrador adm1 = new Administrador();
		adm1.setNome("Tales Batista Admin");
		adm1.setCpf("52922098133");
		adm1.setUsuario(usuarioAdmin);
		
		adminRepo.save(adm1);

	}

}
