package Correia.Viriato.Principal.Service.Professor;

import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    private final DataSource dataSource;

    public ProfessorService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<ProfessorDTO> listarPorTurma(String turmaId) throws Exception {
        List<ProfessorDTO> professores = new ArrayList<>();
        String sql = """
            SELECT pd.professor_id, u.nome, pd.turma_id
            FROM turma_professor pd
            JOIN usuario u ON pd.professor_id = u.user_id
            """;

        if (turmaId != null && !turmaId.isEmpty()) {
            sql += " WHERE pd.turma_id = ?";
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (turmaId != null && !turmaId.isEmpty()) {
                stmt.setString(1, turmaId);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProfessorDTO p = new ProfessorDTO();
                p.setUser_id(rs.getString("professor_id"));
                p.setNome(rs.getString("nome"));
                p.setTurma(rs.getString("turma_id"));
                professores.add(p);
            }
        }

        return professores;
    }
}

