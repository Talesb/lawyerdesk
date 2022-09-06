package br.edu.infnet.lawyerdesk.processoms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.lawyerdesk.processoms.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

}
