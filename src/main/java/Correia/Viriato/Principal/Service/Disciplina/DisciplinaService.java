package Correia.Viriato.Principal.Service.Disciplina;

import Correia.Viriato.Principal.Modelos.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaDAO disciplinaDAO;

    @Autowired
    public DisciplinaService(DisciplinaDAO disciplinaDAO) {
        this.disciplinaDAO = disciplinaDAO;
    }

    public List<Disciplina> listarTodasDisciplinas() throws SQLException {
        return disciplinaDAO.listarTodasDisciplinas();
    }

    public List<Disciplina> listarDisciplinasPorTurma(String turmaId) throws SQLException {
        return disciplinaDAO.listarPorTurma(turmaId);
    }
}
