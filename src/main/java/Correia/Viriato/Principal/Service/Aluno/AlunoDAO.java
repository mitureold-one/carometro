package Correia.Viriato.Principal.Service.Aluno;

import Correia.Viriato.Principal.Service.Avalicao.AvaliacaoDAO;
import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AlunoDAO {

    private final JdbcTemplate jdbcTemplate;


    public AlunoDAO(JdbcTemplate jdbcTemplate, AvaliacaoDAO avaliacaoDAO) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Completa dados do aluno no DTO
    public AlunoDTO buscarAlunoPorUserId(String userId) {
        String sql = "SELECT * FROM aluno WHERE aluno_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
            AlunoDTO aluno = new AlunoDTO();
            aluno.setMatricula(rs.getString("matricula"));
            aluno.setTelefone_aluno(rs.getString("telefone_aluno"));
            aluno.setTelefone_responsavel(rs.getString("telefone_responsavel"));
            return aluno;
        });
    }


    // Salvar aluno
    public void salvarAluno(String idUser, String matricula, String telefoneAluno, String telefoneResponsavel) {
        String sql = "INSERT INTO aluno(aluno_id, matricula, telefone_aluno, telefone_responsavel) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, idUser, matricula, telefoneAluno, telefoneResponsavel);
    }

    // Optional: RowMapper para reutilização
    private static class AlunoRowMapper implements RowMapper<UsuarioDTO> {
        @Override
        public UsuarioDTO mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setMatricula(rs.getString("matricula"));
            dto.setTelefone_aluno(rs.getString("telefone_aluno"));
            dto.setTelefone_responsavel(rs.getString("telefone_responsavel"));
            return dto;
        }
    }
}

