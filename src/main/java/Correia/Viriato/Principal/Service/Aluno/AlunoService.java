package Correia.Viriato.Principal.Service.Aluno;

import Correia.Viriato.Principal.Service.Avalicao.AvaliacaoService;
import Correia.Viriato.Principal.Service.Disciplina.DisciplinaDAO;
import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class AlunoService {

    private final AlunoDAO alunoDAO;
    private final DisciplinaDAO disciplinaDAO;
    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AlunoService(AlunoDAO alunoDAO,
                        DisciplinaDAO disciplinaDAO,
                        AvaliacaoService avaliacaoService) {
        this.alunoDAO = alunoDAO;
        this.disciplinaDAO = disciplinaDAO;
        this.avaliacaoService = avaliacaoService;
    }

    // Recebe o UsuarioDTO e completa com dados do aluno + médias
    public AlunoDTO completarPerfilAluno(UsuarioDTO usuario, AlunoDTO dto) throws SQLException {
        AlunoDTO aluno = new AlunoDTO();

        // Dados vindos do UsuarioDTO
        aluno.setUser_id(usuario.getUser_id());
        aluno.setNome(usuario.getNome());
        aluno.setE_mail(usuario.getE_mail());
        aluno.setFoto(usuario.getFoto());
        aluno.setAno_nascimento(usuario.getAno_nascimento());
        aluno.setTipo(usuario.getTipo());
        aluno.calcularIdade();

        // Dados vindos do AlunoDTO (matrícula e telefones)
        aluno.setMatricula(dto.getMatricula());
        aluno.setTelefone_aluno(dto.getTelefone_aluno());
        aluno.setTelefone_responsavel(dto.getTelefone_responsavel());

        // Calcular médias do aluno (opcional, mas você pediu para manter)
        Map<String, Double> medias = avaliacaoService.calcularMediaPorDisciplina(usuario.getUser_id());
        double mediaGeral = avaliacaoService.calcularMediaGeral(usuario.getUser_id());

        aluno.setMediasPorDisciplina(medias);
        aluno.setMediaGeral(mediaGeral);

        // Apenas retorna — sem salvar no banco
        return aluno;
    }

}



