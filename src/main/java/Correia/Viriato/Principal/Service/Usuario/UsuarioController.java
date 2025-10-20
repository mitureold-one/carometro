package Correia.Viriato.Principal.Service.Usuario;

import Correia.Viriato.Principal.Modelos.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialBlob;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastroUsuario(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = new Usuario(
                    usuarioService.gerarIdUser(dto),
                    convertFoto(dto.getFoto()),
                    dto.getNome(),
                    dto.getE_mail(),
                    dto.getSenha(),
                    dto.getAno_nascimento(),
                    dto.getTipo()
            );
            usuarioService.cadastroUsuario(usuario, dto);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosPorTipo(@RequestParam String tipo) {
        try {
            List<UsuarioDTO> usuarios = usuarioService.listarUsuariosPorTipoDTO(tipo);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable String id) {
        try {
            UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
            if (usuario == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Método util para converter foto Base64 em Blob
    private SerialBlob convertFoto(String fotoBase64) throws Exception {
        if (fotoBase64 == null || fotoBase64.isEmpty()) return null;
        byte[] bytes = Base64.getDecoder().decode(fotoBase64);
        return new SerialBlob(bytes);
    }
}





