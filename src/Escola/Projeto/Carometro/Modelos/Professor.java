package Escola.Projeto.Carometro.Modelos;

public class Professor extends Usuario{
    private String disciplina;
    private String turma;


    public Professor(String idusuario, String nome, String anonascimeto, String email, String senha, String numero) {
        super(idusuario, nome, anonascimeto, email, senha, numero);
    }
}
