package Correia.Viriato.Principal.Service.Avalicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}





