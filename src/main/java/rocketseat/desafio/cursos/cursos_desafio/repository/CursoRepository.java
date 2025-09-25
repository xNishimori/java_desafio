package rocketseat.desafio.cursos.cursos_desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.desafio.cursos.cursos_desafio.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
