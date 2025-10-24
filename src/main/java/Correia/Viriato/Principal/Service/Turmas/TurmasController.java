package Correia.Viriato.Principal.Service.Turmas;

import Correia.Viriato.Principal.Modelos.Turmas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/turmas")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class TurmasController {

    @Autowired
    private TurmasService turmasService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastroTurmas(@RequestBody TurmasDTO dto) throws SQLException {
        Turmas turma = new Turmas(
                turmasService.gerarIdTurma(dto),
                dto.nome_turma(),
                dto.capacidade(),
                dto.turno()
        );
        turmasService.cadastroTurmas(turma);
        return ResponseEntity.ok("Recebido com sucesso!");
    }

    @PostMapping("/{turma_Id}/adicionarAluno/{aluno_Id}")
    public ResponseEntity<String> adicionarAlunoNaTurma(
            @PathVariable String turma_Id,
            @PathVariable String aluno_Id) throws SQLException {

        turmasService.adicionarAlunoNaTurma(turma_Id, aluno_Id);
        return ResponseEntity.ok("Aluno adicionado à turma com sucesso!");
    }

    @PostMapping("/{turma_Id}/adicionarProfessor/{professor_Id}")
    public ResponseEntity<String> adicionarProfessorNaTurma(
            @PathVariable String turma_Id,
            @PathVariable String professor_Id) throws SQLException {

        turmasService.adicionarProfessorNaTurma(turma_Id, professor_Id);
        return ResponseEntity.ok("Professor adicionado à turma com sucesso!");
    }

    @GetMapping("/{turma_Id}")
    public ResponseEntity<TurmaCompletaDTO> buscarTurma(@PathVariable String turma_Id) throws SQLException {
        TurmaCompletaDTO turmaCompleta = turmasService.buscarTurmaCompletaDTO(turma_Id);
        if (turmaCompleta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turmaCompleta);
    }

    @GetMapping
    public ResponseEntity<List<Turmas>> listarTodasTurmas() throws SQLException {
        List<Turmas> turmas = turmasService.listarTodasTurmas();
        return ResponseEntity.ok(turmas);
    }

}
