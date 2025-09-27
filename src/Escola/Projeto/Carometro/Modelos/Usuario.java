package Escola.Projeto.Carometro.Modelos;

public class Usuario {
    private String nome;
    private String anonascimeto;
    private String email;
    private String senha;
    private String numero;

    public Usuario( String nome, String anonascimeto, String email, String senha, String numero) {
        this.nome = nome;
        this.anonascimeto = anonascimeto;
        this.email = email;
        this.senha = senha;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public String getAnonascimeto() {
        return anonascimeto;
    }

    public String getEmail() {
        return email;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return nome +" "+ anonascimeto +" "+email +" "+ senha + numero;
    }
}
