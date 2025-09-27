package Escola.Projeto.Carometro.Modelos;

public class Professor extends Usuario{
    private String disciplina;
    private String turma;

    //*iniciando instâncias com o construtor da super classe 'Usuario'*//
    public Professor(String nome, String anonascimeto, String email, String senha, String numero) {
        super(nome, anonascimeto, email, senha, numero);
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}
