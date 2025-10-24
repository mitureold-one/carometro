package Correia.Viriato.Principal.Service.Avalicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    public void atualizarAvaliacao(AvaliacaoDTO dto) throws SQLException {
        avaliacaoDAO.atualizarAvaliacao(dto);
    }

    /**
     * Calcula a média das avaliações do aluno em cada disciplina.
     * Retorna um mapa: nome da disciplina → média.
     */
    public Map<String, Double> calcularMediaPorDisciplina(String alunoId) throws SQLException {
        List<AvaliacaoDTO> avaliacoes = avaliacaoDAO.listarPorAluno(alunoId);
        if (avaliacoes.isEmpty()) return Map.of();

        // Agrupar por nome da disciplina (melhor pra exibir)
        Map<String, List<AvaliacaoDTO>> porDisciplina = avaliacoes.stream()
                .collect(Collectors.groupingBy(AvaliacaoDTO::getNome_disciplina));

        Map<String, Double> mediasPorDisciplina = new HashMap<>();

        for (Map.Entry<String, List<AvaliacaoDTO>> entry : porDisciplina.entrySet()) {
            String nomeDisciplina = entry.getKey();
            List<AvaliacaoDTO> avals = entry.getValue();

            // Calcular a média individual de cada avaliação
            double soma = 0;
            for (AvaliacaoDTO a : avals) {
                double mediaAvaliacao = (a.getSociabilidade() + a.getParticipacao()
                        + a.getResponsabilidade() + a.getAssiduidade()) / 4.0;
                soma += mediaAvaliacao;
            }

            // Média final da disciplina
            double mediaFinal = soma / avals.size();
            mediasPorDisciplina.put(nomeDisciplina, mediaFinal);
        }

        return mediasPorDisciplina;
    }

    /**
     * Calcula a média geral do aluno com base nas médias por disciplina.
     */
    public double calcularMediaGeral(String alunoId) throws SQLException {
        Map<String, Double> medias = calcularMediaPorDisciplina(alunoId);
        if (medias.isEmpty()) return 0;

        double soma = medias.values().stream().mapToDouble(Double::doubleValue).sum();
        return soma / medias.size();
    }
}




