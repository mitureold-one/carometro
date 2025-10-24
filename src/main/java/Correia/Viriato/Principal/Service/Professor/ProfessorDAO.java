package Correia.Viriato.Principal.Service.Professor;


import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProfessorDAO {

    private final JdbcTemplate jdbcTemplate;

    public ProfessorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Completa dados do professor no DTO
    public UsuarioDTO completarDadosProfessor(UsuarioDTO usuario) {
        String sql = "SELECT * FROM professor WHERE professor_id = ?";
        jdbcTemplate.queryForObject(sql, new Object[]{usuario.getUser_id()}, (rs, rowNum) -> {
            usuario.setNumeroContrato(rs.getString("numero_contrato"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setFormacao(rs.getString("formacao")); // coluna sem acento
            usuario.setSalario(rs.getDouble("salario"));
            usuario.setCategoria(rs.getString("categoria"));
            return usuario;
        });
        return usuario;
    }

    // Salvar professor
    public void salvarProfessor(String idUser, String numeroContrato, String telefone,
                                String formacao, Double salario, String categoria) {
        String sql = "INSERT INTO professor(professor_id, numero_contrato, telefone, formacao, salario, categoria) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, idUser, numeroContrato, telefone, formacao, salario, categoria);
    }

    // Optional: RowMapper para reutilização
    private static class ProfessorRowMapper implements RowMapper<UsuarioDTO> {
        @Override
        public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setNumeroContrato(rs.getString("numero_contrato"));
            dto.setTelefone(rs.getString("telefone"));
            dto.setFormacao(rs.getString("formacao"));
            dto.setSalario(rs.getDouble("salario"));
            dto.setCategoria(rs.getString("categoria"));
            return dto;
        }
    }
}

