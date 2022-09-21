package br.edu.infnet.lawyerdesk.processoms.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.lawyerdesk.processoms.service.ProcessoService;

@Component
public class ProcessoMsLoader implements ApplicationRunner {

	@Autowired
	private ProcessoService processoService;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		
//		ProcessoDTO dto = new ProcessoDTO();
//		dto.setIdsAdvogados(List.of(1L));
//		dto.setNumero("111");
//		dto.setStatus(StatusProcesso.INICIADO);
//		
//		this.processoService.save(dto);
//		
//		dto.setId(1L);
//		dto.setIdsAdvogados(List.of(2L));
//		this.processoService.save(dto);
		
	}
}
