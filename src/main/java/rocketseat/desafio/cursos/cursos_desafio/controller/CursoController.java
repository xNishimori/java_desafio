package rocketseat.desafio.cursos.cursos_desafio.controller;

import jakarta.annotation.Nonnull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocketseat.desafio.cursos.cursos_desafio.dto.CursoDTO;
import rocketseat.desafio.cursos.cursos_desafio.model.Curso;
import rocketseat.desafio.cursos.cursos_desafio.servide.CursoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private CursoService service;

    public CursoController(CursoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Curso> cadastrar(@Nonnull @RequestBody CursoDTO dto){
        Curso saved = service.Criar(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@Nonnull @PathVariable Long id, @RequestBody CursoDTO dto){
        return ResponseEntity.of(service.atualizarId(id, dto));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<?> atualizarActive(@PathVariable Long id, @RequestBody Map<String, Boolean> body){
        if(!body.containsKey("active")){
            return ResponseEntity.badRequest().body("Parametro invalido");
        }
        Curso atualizado = service.atualizarActive(id, body.get("active"));
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> deleteCurso(@PathVariable Long id){
        service.deleteCursoId(id);
        return ResponseEntity.noContent().build();
    }

}
