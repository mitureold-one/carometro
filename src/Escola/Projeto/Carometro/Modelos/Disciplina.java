package Escola.Projeto.Carometro.Modelos;

import java.util.List;

public class Disciplina {
    private String nome;
    private List<Alunos> alunos;
    private List<Professor> professores;

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public void getnome () {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "nome= " + nome;
    }

    public String getNome() {
        return this.nome = nome;
    }
}
