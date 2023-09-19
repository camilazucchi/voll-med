package med.voll.api.repository;

import med.voll.api.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
/* JPA Repository é uma parte do Spring Data JPA que simplifica muito o desenvolvimento de repositórios
de dados para entidades JPA (como nossa classe "Medico") e elimina a necessidade de escrever implementações
de repositório manualmente. */

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
