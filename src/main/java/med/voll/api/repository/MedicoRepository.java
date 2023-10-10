package med.voll.api.repository;

import med.voll.api.entity.Medico;
import med.voll.api.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
/* JPA Repository é uma parte do Spring Data JPA que simplifica muito o desenvolvimento de repositórios
de dados para entidades JPA (como nossa classe "Medico") e elimina a necessidade de escrever implementações
de repositório manualmente. */

/* Esse repositório fornece métodos de consulta padrão e uma consulta personalizada para encontrar médicos ativos
e selecionar aleatoriamente um médico disponível em uma determinada especialidade e data. Ele facilita a interação
com a entidade "Medico" e a execução de consultas personalizadas relacionadas a médicos no banco de dados. */
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    // A anotação "@Query" é usada em tecnologias de persistência de dados, como o JPA (Java Persistence API) no
    // contexto do Java, para definir consultas personalizadas em métodos de repositório.
    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            AND
            m.especialidade = :especialidade
            AND
            m.id NOT IN(
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.data = :data
            )
            ORDER BY RAND()
            LIMIT 1
            """)
            // "SELECT m FROM Medico m": Seleciona objetos da entidade "Medico".
            // "WHERE m.ativo = true": Filtra apenas os médicos ativos.
            // "AND m.especialidade = :especialidade": Filtra por especialidade.
            // "AND m.id NOT IN (...)": Exclui médicos cujos IDs não estão na subconsulta.
            // "ORDER BY RAND() LIMIT 1": Ordena aleatoriamente os resultados e limita o resultado a apenas
            // um médico.

    Medico selectRandomDoctorOnDate(Especialidade especialidade, LocalDateTime data);
}
