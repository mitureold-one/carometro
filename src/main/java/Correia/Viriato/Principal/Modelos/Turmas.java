package Correia.Viriato.Principal.Modelos;

import java.util.List;

public class Turmas {
    private String turma_id;
    private String nome_turma;
    private String capacidade;
    private String turno;

    private Professor professor_responsavel;
    private List<Professor> professores;
    private List<Alunos> alunos;

    public Turmas(String turma_id, String nome_turma, String capacidade, String turno) {
        this.turma_id = turma_id;
        this.nome_turma = nome_turma;
        this.capacidade = capacidade;
        this.turno = turno;
    }

    public String getTurma_id() {
        return turma_id;
    }

    public void setTurma_id(String turma_id) {
        this.turma_id = turma_id;
    }

    public String getNome_turma() {
        return nome_turma;
    }

    public void setNome_turma(String nome_turma) {
        this.nome_turma = nome_turma;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Professor getProfessor_responsavel() {
        return professor_responsavel;
    }

    public void setProfessor_responsavel(Professor professor_responsavel) {
        this.professor_responsavel = professor_responsavel;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Alunos> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Alunos> alunos) {
        this.alunos = alunos;
    }
}
