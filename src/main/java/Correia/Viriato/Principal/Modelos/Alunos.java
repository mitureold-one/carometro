package Correia.Viriato.Principal.Modelos;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// Essa Classe representa os alunos no sistema, herda métodos e atributos de Usuario
public class Alunos extends Usuario{

    private Turmas turma;
    private List<Disciplina> disciplinas = new ArrayList<>();

    //* Construtor usando a superclasse Usuario *//
    public Alunos(String user_Id, Blob foto, String nome, String e_mail, String senha, Date ano_nascimento, String tipo) {
        super(user_Id, foto, nome, e_mail, senha, ano_nascimento, tipo);
    }

    // Getters e Setters
    public Turmas getTurma() {
        return turma;
    }

    public void setTurma(Turmas turma) {
        this.turma = turma;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
