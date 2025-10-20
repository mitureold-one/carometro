package Correia.Viriato.Principal.Service.Professor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professores")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    // Endpoint para listar professores filtrando por turma
    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> listarPorTurma(@RequestParam(required = false) String turmaId) {
        try {
            List<ProfessorDTO> professores = professorService.listarPorTurma(turmaId);
            return ResponseEntity.ok(professores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}