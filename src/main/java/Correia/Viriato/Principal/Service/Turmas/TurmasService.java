package Correia.Viriato.Principal.Service.Turmas;

import Correia.Viriato.Principal.Modelos.Turmas;
import Correia.Viriato.Principal.Modelos.Usuario;
import Correia.Viriato.Principal.Service.Avalicao.AvaliacaoDAO;
import Correia.Viriato.Principal.Service.Avalicao.AvaliacaoDTO;
import Correia.Viriato.Principal.Service.Usuario.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TurmasService {

    private final TurmasDAO turmasDAO;
    private final UsuarioDAO usuarioDAO;

    @Autowired
    public TurmasService(TurmasDAO turmasDAO, UsuarioDAO usuarioDAO) {
        this.turmasDAO = turmasDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastroTurmas(Turmas turmas) throws SQLException {
        turmasDAO.salvar(turmas);
    }

    public String gerarIdTurma(TurmasDTO dto) throws SQLException {
        String prefixo;
        switch (dto.turno().toLowerCase()) {
            case "matutino" -> prefixo = "MA";
            case "vespertino" -> prefixo = "VE";
            case "integral" -> prefixo = "IN";
            default -> prefixo = "HH";
        }
        int numero = turmasDAO.buscarProximoNumero(prefixo);
        return prefixo + String.format("%03d", numero);
    }

    public void adicionarAlunoNaTurma(String turmaId, String alunoId) throws SQLException {
        turmasDAO.adicionarAlunoNaTurma(turmaId, alunoId);
    }

    public void adicionarProfessorNaTurma(String turmaId, String professorId) throws SQLException {
        turmasDAO.adicionarProfessorNaTurma(turmaId, professorId);
    }


    public List<Turmas> listarTodasTurmas() throws SQLException {
        return turmasDAO.listarTodasTurmas();
    }

    public TurmaCompletaDTO buscarTurmaCompletaDTO(String turmaId) throws SQLException {
        return turmasDAO.buscarTurmaCompletaDTO(turmaId);
    }



}

