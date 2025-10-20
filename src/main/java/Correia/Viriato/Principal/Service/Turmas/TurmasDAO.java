package Correia.Viriato.Principal.Service.Turmas;

import Correia.Viriato.Principal.Modelos.Turmas;
import Correia.Viriato.Principal.Service.Avalicao.AvaliacaoDTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class TurmasDAO {

    private final DataSource dataSource;

    public TurmasDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void salvar(Turmas turmas) throws SQLException {
        String sql = "INSERT INTO turma(turma_id, nome_turma, turno, capacidade) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, turmas.getTurma_id());
            ps.setString(2, turmas.getNome_turma());
            ps.setString(3, turmas.getTurno());
            ps.setInt(4, Integer.parseInt(turmas.getCapacidade())); // alterar para int
            ps.executeUpdate();
        }
    }

    public int buscarProximoNumero(String prefixo) throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(turma_id, 3) AS UNSIGNED)) FROM turma WHERE turma_id LIKE ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prefixo + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) + 1;
            return 1;
        }
    }

    public void adicionarAlunoNaTurma(String turmaId, String alunoId) throws SQLException {
        String sql = "INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turmaId);
            stmt.setString(2, alunoId);
            stmt.executeUpdate();
        }
    }

    public void adicionarProfessorNaTurma(String turmaId, String professorId) throws SQLException {
        String sql = "INSERT INTO turma_professor (turma_id, professor_id) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turmaId);
            stmt.setString(2, professorId);
            stmt.executeUpdate();
        }
    }

    public List<Turmas> listarTodasTurmas() throws SQLException {
        String sql = "SELECT * FROM turma";
        List<Turmas> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Turmas t = new Turmas(
                        rs.getString("turma_id"),
                        rs.getString("nome_turma"),
                        rs.getString("capacidade"),
                        rs.getString("turno")
                );
                lista.add(t);
            }
        }
        return lista;
    }

    public TurmaCompletaDTO buscarTurmaCompletaDTO(String turmaId) throws SQLException {
        String sqlTurma = "SELECT * FROM turma WHERE turma_id = ?";
        String sqlAlunos = """
                SELECT u.user_id, u.nome, u.e_mail, u.foto
                FROM turma_aluno ta
                JOIN usuario u ON ta.aluno_id = u.user_id
                WHERE ta.turma_id = ?
                """;
        String sqlProfessores = """
                SELECT u.user_id, u.nome, u.e_mail, u.foto
                FROM turma_professor tp
                JOIN usuario u ON tp.professor_id = u.user_id
                WHERE tp.turma_id = ?
                """;

        try (Connection conn = dataSource.getConnection()) {

            // 🔹 Dados da turma
            String turmaIdDb = null, nomeTurma = null, turno = null;
            int capacidade = 0;
            try (PreparedStatement stmt = conn.prepareStatement(sqlTurma)) {
                stmt.setString(1, turmaId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    turmaIdDb = rs.getString("turma_id");
                    nomeTurma = rs.getString("nome_turma");
                    turno = rs.getString("turno");
                    capacidade = rs.getInt("capacidade");
                } else {
                    return null; // turma não encontrada
                }
            }

            // 🔹 Alunos
            List<UsuarioResumoDTO> alunos = new ArrayList<>();
            try (PreparedStatement stmt = conn.prepareStatement(sqlAlunos)) {
                stmt.setString(1, turmaId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String fotoBase64 = null;
                    byte[] fotoBytes = rs.getBytes("foto");
                    if (fotoBytes != null && fotoBytes.length > 0) {
                        fotoBase64 = Base64.getEncoder().encodeToString(fotoBytes);
                    }
                    alunos.add(new UsuarioResumoDTO(
                            rs.getString("user_id"),
                            rs.getString("nome"),
                            rs.getString("e_mail"),
                            fotoBase64
                    ));
                }
            }

            // 🔹 Professores
            List<UsuarioResumoDTO> professores = new ArrayList<>();
            try (PreparedStatement stmt = conn.prepareStatement(sqlProfessores)) {
                stmt.setString(1, turmaId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String fotoBase64 = null;
                    byte[] fotoBytes = rs.getBytes("foto");
                    if (fotoBytes != null && fotoBytes.length > 0) {
                        fotoBase64 = Base64.getEncoder().encodeToString(fotoBytes);
                    }
                    professores.add(new UsuarioResumoDTO(
                            rs.getString("user_id"),
                            rs.getString("nome"),
                            rs.getString("e_mail"),
                            fotoBase64
                    ));
                }
            }

            return new TurmaCompletaDTO(turmaIdDb, nomeTurma, capacidade, turno, alunos, professores);
        }
    }


}
