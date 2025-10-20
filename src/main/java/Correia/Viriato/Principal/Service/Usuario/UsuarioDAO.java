package Correia.Viriato.Principal.Service.Usuario;

import Correia.Viriato.Principal.Modelos.Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Repository
public class UsuarioDAO {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Salvar usuário
    public void salvar(Usuario usuario) {
        String sql = """
                INSERT INTO usuario(user_Id, foto, nome, e_mail, senha, ano_nascimento, tipo)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try {
            jdbcTemplate.update(sql,
                    usuario.getUser_Id(),
                    usuario.getFoto(),
                    usuario.getNome(),
                    usuario.getE_mail(),
                    usuario.getSenha(),
                    usuario.getAno_nascimento(),
                    usuario.getTipo()
            );
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("O e-mail informado já está cadastrado.", e);
        }
    }

    // Buscar próximo número de usuário
    public int buscarProximoNumero(String prefixo) {
        String sql = "SELECT MAX(CAST(SUBSTRING(user_Id, 3) AS UNSIGNED)) FROM usuario WHERE user_Id LIKE ?";
        Integer max = jdbcTemplate.queryForObject(sql, new Object[]{prefixo + "%"}, Integer.class);
        return (max != null) ? max + 1 : 1;
    }

    // Listar usuários por tipo
    public List<Usuario> listarPorTipo(String tipo) {
        String sql = "SELECT * FROM usuario WHERE tipo = ?";
        return jdbcTemplate.query(sql, new Object[]{tipo}, new UsuarioRowMapper());
    }

    // Buscar usuário por ID e converter para DTO
    public UsuarioDTO buscarUsuarioPorId(String userId) {
        String sql = "SELECT * FROM usuario WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setUser_id(rs.getString("user_id"));
            dto.setNome(rs.getString("nome"));
            dto.setE_mail(rs.getString("e_mail"));
            dto.setTipo(rs.getString("tipo"));
            dto.setAno_nascimento(rs.getDate("ano_nascimento"));

            // Idade
            if (rs.getDate("ano_nascimento") != null) {
                dto.calcularIdade();
            }

            // Foto
            byte[] fotoBytes = rs.getBytes("foto");
            if (fotoBytes != null && fotoBytes.length > 0) {
                dto.setFoto(Base64.getEncoder().encodeToString(fotoBytes));
            }
            return dto;
        });
    }

    // RowMapper para Usuario
    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Usuario(
                    rs.getString("user_id"),
                    null, // foto pode ser tratada depois
                    rs.getString("nome"),
                    rs.getString("e_mail"),
                    rs.getString("senha"),
                    rs.getDate("ano_nascimento"),
                    rs.getString("tipo")
            );
        }
    }
}

