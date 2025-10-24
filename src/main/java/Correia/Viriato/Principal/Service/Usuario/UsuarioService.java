package Correia.Viriato.Principal.Service.Usuario;

import Correia.Viriato.Principal.Modelos.Usuario;
import Correia.Viriato.Principal.Service.Adm.AdministradorDAO;
import Correia.Viriato.Principal.Service.Aluno.AlunoDAO;
import Correia.Viriato.Principal.Service.Aluno.AlunoDTO;
import Correia.Viriato.Principal.Service.Aluno.AlunoService;
import Correia.Viriato.Principal.Service.Avalicao.AvaliacaoDAO;
import Correia.Viriato.Principal.Service.Disciplina.DisciplinaDAO;
import Correia.Viriato.Principal.Service.Professor.ProfessorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final AlunoDAO alunoDAO;
    private final ProfessorDAO professorDAO;
    private final AdministradorDAO administradorDAO;

    @Autowired
    private DisciplinaDAO disciplinaDAO;

    @Autowired
    private AvaliacaoDAO avaliacaoDAO;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    public UsuarioService(UsuarioDAO usuarioDAO,
                          AlunoDAO alunoDAO,
                          ProfessorDAO professorDAO,
                          AdministradorDAO administradorDAO) {
        this.usuarioDAO = usuarioDAO;
        this.alunoDAO = alunoDAO;
        this.professorDAO = professorDAO;
        this.administradorDAO = administradorDAO;
    }

    // Cadastro de usuário
    public void cadastroUsuario(Usuario usuario, UsuarioDTO dto) throws SQLException {
        // Salva a entidade Usuario
        usuarioDAO.salvar(usuario);

        // Salva dados específicos de acordo com o tipo
        String tipo = dto.getTipo().toUpperCase();
        switch (tipo) {
            case "ALUNO" -> alunoDAO.salvarAluno(
                    usuario.getUser_Id(),
                    dto.getMatricula(),
                    dto.getTelefone_aluno(),
                    dto.getTelefone_responsavel()
            );
            case "PROFESSOR" -> professorDAO.salvarProfessor(
                    usuario.getUser_Id(),
                    dto.getNumeroContrato(),
                    dto.getTelefone(),
                    dto.getFormacao(),
                    dto.getSalario(),
                    dto.getCategoria()
            );
            case "ADMIN", "ADM" -> administradorDAO.salvarAdministrador(
                    usuario.getUser_Id(),
                    dto.getSetor()
            );
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + dto.getTipo());
        }
    }

    // Gera ID de usuário com prefixo
    public String gerarIdUser(UsuarioDTO dto) throws SQLException {
        String prefixo = switch (dto.getTipo().toLowerCase()) {
            case "aluno" -> "AL";
            case "professor" -> "PR";
            case "admin", "adm" -> "AD";
            default -> "US";
        };
        int numero = usuarioDAO.buscarProximoNumero(prefixo);
        return prefixo + String.format("%03d", numero);
    }

    // Listar usuários por tipo
    public List<Usuario> listarUsuariosPorTipo(String tipo) throws SQLException {
        return usuarioDAO.listarPorTipo(tipo);
    }

    // Buscar usuário completo por ID
    public UsuarioDTO buscarUsuarioPorId(String userId) throws SQLException {
        UsuarioDTO usuario = usuarioDAO.buscarUsuarioPorId(userId);

        if (usuario == null) return null;

        String tipo = usuario.getTipo() != null ? usuario.getTipo().toUpperCase() : "";

        switch (tipo) {
            case "ALUNO":
                // Buscar os dados específicos do aluno (matrícula e telefones)
                AlunoDTO dadosAluno = alunoDAO.buscarAlunoPorUserId(userId);
                // Unir os dados e retornar
                AlunoDTO alunoCompleto = alunoService.completarPerfilAluno(usuario, dadosAluno);
                return alunoCompleto;

            case "PROFESSOR":
                return professorDAO.completarDadosProfessor(usuario);

            case "ADMIN":
            case "ADM":
                return administradorDAO.completarDadosAdmin(usuario);

            default:
                return usuario;
        }
    }


    public List<UsuarioDTO> listarUsuariosPorTipoDTO(String tipo) throws SQLException {
        List<Usuario> usuarios = listarUsuariosPorTipo(tipo); // método existente
        List<UsuarioDTO> dtos = new ArrayList<>();

        for (Usuario u : usuarios) {
            UsuarioDTO dto = buscarUsuarioPorId(u.getUser_Id()); // já preenche todos os dados
            if (dto != null) dtos.add(dto);
        }

        return dtos;
    }

    public List<UsuarioDTO> listarAlunosSemTurma() {
        return usuarioDAO.listarAlunosSemTurma();
    }
}

