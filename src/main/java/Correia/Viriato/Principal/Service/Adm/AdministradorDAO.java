package Correia.Viriato.Principal.Service.Adm;

import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdministradorDAO {

    private final JdbcTemplate jdbcTemplate;

    public AdministradorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Completa dados do administrador no DTO
    public UsuarioDTO completarDadosAdmin(UsuarioDTO usuario) {
        String sql = "SELECT * FROM administradores WHERE administrador_id = ?";
        jdbcTemplate.queryForObject(sql, new Object[]{usuario.getUser_id()}, (rs, rowNum) -> {
            usuario.setSetor(rs.getString("setor"));
            return usuario;
        });
        return usuario;
    }

    // Salvar administrador
    public void salvarAdministrador(String idUser, String setor) {
        String sql = "INSERT INTO administrador(administrador_id, setor) VALUES (?,?)";
        jdbcTemplate.update(sql, idUser, setor);
    }

    // Optional: RowMapper para reutilização
    private static class AdministradorRowMapper implements RowMapper<UsuarioDTO> {
        @Override
        public UsuarioDTO mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setSetor(rs.getString("setor"));
            return dto;
        }
    }
}

