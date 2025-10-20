package Correia.Viriato.Principal.Service.Professor;

import Correia.Viriato.Principal.Modelos.Disciplina;
import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;
import java.util.List;

public class ProfessorDTO extends UsuarioDTO {

    private String telefone;
    private Double salario;
    private String numeroContrato;
    private String formacao;
    private List<Disciplina> disciplinas;
    private String turma;

    // Getters e Setters
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public String getNumeroContrato() { return numeroContrato; }
    public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }

    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }

    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<Disciplina> disciplinas) { this.disciplinas = disciplinas; }

    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }
}



