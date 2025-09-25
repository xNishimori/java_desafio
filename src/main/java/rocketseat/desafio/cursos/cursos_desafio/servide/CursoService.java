package rocketseat.desafio.cursos.cursos_desafio.servide;

import org.springframework.stereotype.Service;
import rocketseat.desafio.cursos.cursos_desafio.dto.CursoDTO;
import rocketseat.desafio.cursos.cursos_desafio.model.Curso;
import rocketseat.desafio.cursos.cursos_desafio.repository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso Criar(CursoDTO dto){
        Curso curso = new Curso();
        curso.setName(dto.getName());
        curso.setCategory(dto.getCategory());
        curso.setActive(dto.isActive());
        curso.setCreated_at(dto.getCreated_at());
        curso.setUpdated_at(dto.getUpdate_at());

        return repository.save(curso);
    }

    public List<Curso> listarTodos(){
        return repository.findAll();
    }

    public Optional<Curso> atualizarId(Long id, CursoDTO dto){
        return repository.findById(id).map(curso -> {
            if(dto.getName() != null) curso.setName(dto.getName());
            if(dto.getCategory() != null) curso.setCategory(dto.getCategory());

            return repository.save(curso);
        });
    }

    public Curso atualizarActive(Long id, Boolean active) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id não encontrado"));
        curso.setActive(active);

        return repository.save(curso);
    }

    public void deleteCursoId(Long id){
        Optional<Curso> curso = repository.findById(id);
        repository.deleteById(id);
    }
}
