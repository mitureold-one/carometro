package Correia.Viriato.Principal.Service.Avalicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("/lancar")
    public ResponseEntity<String> lancarAvaliacao(@RequestBody AvaliacaoDTO dto) {
        try {
            avaliacaoService.salvarAvaliacao(dto);
            return ResponseEntity.ok("Avaliação lançada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar avaliação: " + e.getMessage());
        }
    }

    @GetMapping("/aluno/{alunoId}/disciplina/{disciplinaId}")
    public ResponseEntity<AvaliacaoDTO> buscarPorAlunoEDisciplina(
            @PathVariable String alunoId,
            @PathVariable String disciplinaId) {
        try {
            AvaliacaoDTO dto = avaliacaoService.buscarPorAlunoEDisciplina(alunoId, disciplinaId);
            if (dto == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<AvaliacaoDTO>> listarPorAluno(@PathVariable String alunoId) {
        try {
            return ResponseEntity.ok(avaliacaoService.listarPorAluno(alunoId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarAvaliacao(@RequestBody AvaliacaoDTO dto) {
        try {
            avaliacaoService.atualizarAvaliacao(dto);
            return ResponseEntity.ok("Avaliação atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar avaliação: " + e.getMessage());
        }
    }

    @GetMapping("/{turmaId}/avaliacaoGeral")
    public ResponseEntity<Map<String, Object>> getAvaliacaoGeralTurma(@PathVariable String turmaId) {
        try {
            Map<String, Object> resultado = avaliacaoService.calcularMediaTurma(turmaId);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mediaTurma", 0.0, "mediasAlunos", Collections.emptyMap()));
        }
    }

    @GetMapping("/geral/{alunoId}")
    public ResponseEntity<Double> getAvaliacaoGeral(@PathVariable String alunoId) {
        try {
            double media = avaliacaoService.calcularAvaliacaoGeral(alunoId);
            return ResponseEntity.ok(media);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}





