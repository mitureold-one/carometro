package Correia.Viriato.Principal.Modelos;


import java.sql.Blob;
import java.sql.Date;

public class Professor extends Usuario {
    private String disciplina;
    private Turmas turma;


    public Professor(String user_Id, Blob foto, String nome, String e_mail, String senha, Date ano_nascimento, String tipo) {
        super(user_Id, foto, nome, e_mail, senha, ano_nascimento, tipo);
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Turmas getTurma() {
        return turma;
    }

    public void setTurma(Turmas turma) {
        this.turma = turma;
    }
}

