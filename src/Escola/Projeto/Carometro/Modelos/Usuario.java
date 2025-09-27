package Escola.Projeto.Carometro.Modelos;

public class Usuario {
    private String idusuario;
    private String nome;
    private String anonascimeto;
    private String email;
    private String senha;
    private String numero;

    public Usuario(String idusuario, String nome, String anonascimeto, String email, String senha, String numero) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.anonascimeto = anonascimeto;
        this.email = email;
        this.senha = senha;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "idusuario='" + idusuario + '\'' +
                ", nome='" + nome + '\'' +
                ", anonascimeto='" + anonascimeto + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
