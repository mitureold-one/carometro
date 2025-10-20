package Correia.Viriato.Principal.Service.Disciplina;

import Correia.Viriato.Principal.Modelos.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DisciplinaDAO {

    @Autowired
    private DataSource dataSource;

    public List<Disciplina> listarTodasDisciplinas() throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Disciplina d = new Disciplina(
                        rs.getString("disciplina_id"),
                        rs.getString("nome_disciplina"),
                        rs.getString("turma_id")
                );
                disciplinas.add(d);
            }
        }

        return disciplinas;
    }

    public List<Disciplina> listarPorTurma(String turmaId) throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina WHERE turma_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, turmaId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                disciplinas.add(new Disciplina(
                        rs.getString("disciplina_id"),
                        rs.getString("nome"),
                        rs.getString("turma_id")
                ));
            }
        }

        return disciplinas;
    }

    public String buscarNomePorId(String disciplinaId) throws SQLException {
        String sql = "SELECT nome FROM disciplina WHERE disciplina_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, disciplinaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("nome");
        }
        return "Disciplina desconhecida";
    }
}

