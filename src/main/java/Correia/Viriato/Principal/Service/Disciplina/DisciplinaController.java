package Correia.Viriato.Principal.Service.Disciplina;

import Correia.Viriato.Principal.Modelos.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarTodasDisciplinas() throws SQLException {
        return ResponseEntity.ok(disciplinaService.listarTodasDisciplinas());
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<List<Disciplina>> listarPorTurma(@PathVariable String turmaId) throws SQLException {
        return ResponseEntity.ok(disciplinaService.listarDisciplinasPorTurma(turmaId));
    }
}

