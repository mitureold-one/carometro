package Correia.Viriato.Principal.Service.Avalicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar todas as transações com o banco
 * relacionadas às avaliações.
 */
@Repository
public class AvaliacaoDAO {

    @Autowired
    private DataSource dataSource;

    public void salvarAvaliacao(AvaliacaoDTO dto) throws SQLException {
        String sql = """
            INSERT INTO disciplina_avaliacao(
                disciplina_id, aluno_id, data_avaliacao, sociabilidade,
                participacao, responsabilidade, assiduidade
            ) VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date data = dto.getData_avaliacao() != null
                    ? new java.sql.Date(dto.getData_avaliacao().getTime())
                    : new java.sql.Date(System.currentTimeMillis());

            ps.setString(1, dto.getDisciplina_id());
            ps.setString(2, dto.getAluno_id());
            ps.setDate(3, data);
            ps.setInt(4, dto.getSociabilidade());
            ps.setInt(5, dto.getParticipacao());
            ps.setInt(6, dto.getResponsabilidade());
            ps.setInt(7, dto.getAssiduidade());

            ps.executeUpdate();
        }
    }

    public List<AvaliacaoDTO> listarPorAluno(String alunoId) throws SQLException {
        List<AvaliacaoDTO> avaliacoes = new ArrayList<>();
        String sql = """
        SELECT d.disciplina_id,p.nome_disciplina, d.aluno_id, d.data_avaliacao, d.sociabilidade,
               d.participacao, d.responsabilidade, d.assiduidade
        FROM disciplina_avaliacao d
        join disciplina p on p.disciplina_id = d.disciplina_id
        WHERE aluno_id = ?;
    """;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alunoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    avaliacoes.add(mapResultSetParaDTO(rs)); // usa o mapeamento centralizado
                }
            }
        }

        return avaliacoes;
    }

    public void atualizarAvaliacao(AvaliacaoDTO dto) throws SQLException {
        String sql = """
            UPDATE disciplina_avaliacao
            SET sociabilidade = ?, participacao = ?, responsabilidade = ?, assiduidade = ?, data_avaliacao = ?
            WHERE aluno_id = ? AND disciplina_id = ?;
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date data = dto.getData_avaliacao() != null
                    ? new java.sql.Date(dto.getData_avaliacao().getTime())
                    : new java.sql.Date(System.currentTimeMillis());

            ps.setInt(1, dto.getSociabilidade());
            ps.setInt(2, dto.getParticipacao());
            ps.setInt(3, dto.getResponsabilidade());
            ps.setInt(4, dto.getAssiduidade());
            ps.setDate(5, data);
            ps.setString(6, dto.getAluno_id());
            ps.setString(7, dto.getDisciplina_id());

            ps.executeUpdate();
        }
    }

    private AvaliacaoDTO mapResultSetParaDTO(ResultSet rs) throws SQLException {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setDisciplina_id(rs.getString("disciplina_id"));
        dto.setNome_disciplina(rs.getString("nome_disciplina")); // <-- novo campo
        dto.setAluno_id(rs.getString("aluno_id"));
        dto.setData_avaliacao(rs.getDate("data_avaliacao"));
        dto.setSociabilidade(rs.getInt("sociabilidade"));
        dto.setParticipacao(rs.getInt("participacao"));
        dto.setResponsabilidade(rs.getInt("responsabilidade"));
        dto.setAssiduidade(rs.getInt("assiduidade"));
        return dto;
    }
}
