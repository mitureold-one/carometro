package Correia.Viriato.Principal.Modelos;

public class Disciplina {
    private String disciplina_id;
    private String nome;
    private String turma_id;

    public Disciplina() {}

    public Disciplina(String disciplina_id, String nome, String turma_id) {
        this.disciplina_id = disciplina_id;
        this.nome = nome;
        this.turma_id = turma_id;
    }

    public String getDisciplina_id() { return disciplina_id; }
    public void setDisciplina_id(String disciplina_id) { this.disciplina_id = disciplina_id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTurma_id() { return turma_id; }
    public void setTurma_id(String turma_id) { this.turma_id = turma_id; }
}



