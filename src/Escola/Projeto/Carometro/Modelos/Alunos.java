package Escola.Projeto.Carometro.Modelos;

public class Alunos extends Usuario{
    Turmas turma;
    Disciplina disciplinas;

    //*iniciando instâncias com o construtor da super classe 'Usuario'*//
    public Alunos(String nome, String anonascimeto, String email, String senha, String numero) {
        super(nome, anonascimeto, email, senha, numero);
    }

    public void setTurmas(Turmas turma) {
        this.turma = turma;
    }

    public String getTurmas() {
        return  turma.getNome();

    }
    public void setDisciplinas(Disciplina disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void getdisciplinas(Disciplina disciplinas) {
        this.disciplinas = disciplinas;
    }

}
