package Correia.Viriato.Principal.Service.Avalicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AvaliacaoService {

    private final AvaliacaoDAO avaliacaoDAO;

    @Autowired
    public AvaliacaoService(AvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDAO = avaliacaoDAO;
    }

    public void salvarAvaliacao(AvaliacaoDTO dto) throws SQLException {
        avaliacaoDAO.salvarAvaliacao(dto);
    }

    public AvaliacaoDTO buscarPorAlunoEDisciplina(String alunoId, String disciplinaId) throws SQLException {
        return avaliacaoDAO.buscarPorAlunoEDisciplina(alunoId, disciplinaId);
    }

    public List<AvaliacaoDTO> listarPorAluno(String alunoId) throws SQLException {
        return avaliacaoDAO.listarPorAluno(alunoId);
    }

    public void atualizarAvaliacao(AvaliacaoDTO dto) throws SQLException {
        avaliacaoDAO.atualizarAvaliacao(dto);
    }

    // Retorna média geral de cada aluno
    public Map<String, Object> calcularMediaAluno(String alunoId) throws SQLException {
        List<AvaliacaoDTO> avaliacoes = avaliacaoDAO.listarAvaliacoesPorAluno(alunoId);
        Map<String, Double> mediasAlunos = new HashMap<>();

        if (avaliacoes.isEmpty()) {
            return Map.of(
                    "mediaTurma", 0.0,
                    "mediasAlunos", mediasAlunos
            );
        }

        Map<String, List<AvaliacaoDTO>> avaliacoesPorAluno = new HashMap<>();
        for (AvaliacaoDTO a : avaliacoes) {
            avaliacoesPorAluno.computeIfAbsent(a.getAluno_id(), k -> new ArrayList<>()).add(a);
        }

        double somaTurma = 0.0;

        for (Map.Entry<String, List<AvaliacaoDTO>> entry : avaliacoesPorAluno.entrySet()) {
            String alunoId = entry.getKey();
            List<AvaliacaoDTO> avals = entry.getValue();

            double somaAluno = 0.0;
            for (AvaliacaoDTO a : avals) {
                double mediaAvaliacao = (a.getSociabilidade() + a.getParticipacao() + a.getResponsabilidade() + a.getAssiduidade()) / 4.0;
                somaAluno += mediaAvaliacao;
            }
            double mediaFinalAluno = somaAluno / avals.size();
            mediasAlunos.put(alunoId, mediaFinalAluno);
            somaTurma += mediaFinalAluno;
        }

        double mediaTurma = somaTurma / mediasAlunos.size();

        return Map.of(
                "mediaTurma", mediaTurma,
                "mediasAlunos", mediasAlunos
        );
    }

    public double calcularAvaliacaoGeral(String alunoId) throws SQLException {
        List<AvaliacaoDTO> avaliacoes = avaliacaoDAO.listarPorAluno(alunoId);

        if (avaliacoes.isEmpty()) return 0.0;

        double somaTotal = 0;
        int totalItens = 0;

        for (AvaliacaoDTO a : avaliacoes) {
            somaTotal += a.getSociabilidade() + a.getParticipacao() + a.getResponsabilidade() + a.getAssiduidade();
            totalItens += 4; // quatro atributos
        }

        return somaTotal / totalItens; // média geral entre 0 e 10, por exemplo
    }

}



