package Escola.Projeto.Carometro.Modelos;

import java.util.ArrayList;
import java.util.List;

public class Turmas {
    private String idturma;
    private String nome;
    private List<Alunos> alunos;

    //*Construtor da classe 'TURMA'*//
    public Turmas(String idturma, String nome) {
        this.idturma = idturma;
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void addAlunos(Alunos aluno) {
        alunos.add(aluno);
        aluno.setTurmas(this);
    }

    public List<Alunos> listarAlunos(){
        return alunos;
    }

    @Override
    public String toString() {
        return alunos.toString();
    }
}
