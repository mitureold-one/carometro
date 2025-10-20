package Correia.Viriato.Principal.Service.Aluno;

import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;

import java.util.List;
import java.util.Map;


// AlunoDTO herda de UsuarioDTO
public class AlunoDTO extends UsuarioDTO {
    private String telefone_aluno;
    private String telefone_responsavel;
    private String matricula;
    private int avaliacaoGeral;

    private List<Map<String, Object>> disciplinas;

    public List<Map<String, Object>> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<Map<String, Object>> disciplinas) { this.disciplinas = disciplinas; }

    public int getAvaliacaoGeral() {
        return avaliacaoGeral;
    }

    public void setAvaliacaoGeral(int avaliacaoGeral) {
        this.avaliacaoGeral = avaliacaoGeral;
    }

    public String getTelefone_aluno() {
        return telefone_aluno;
    }

    public void setTelefone_aluno(String telefone_aluno) {
        this.telefone_aluno = telefone_aluno;
    }

    public String getTelefone_responsavel() {
        return telefone_responsavel;
    }

    public void setTelefone_responsavel(String telefone_responsavel) {
        this.telefone_responsavel = telefone_responsavel;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
