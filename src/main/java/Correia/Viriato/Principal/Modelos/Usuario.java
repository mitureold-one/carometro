package Correia.Viriato.Principal.Modelos;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.Date;
import java.util.Base64;

//* Essa SUPER-Classe é responsavel por representar os usarios no sistema: alunos, professores e administradores*//
public class Usuario {
    private String user_Id;
    private Blob foto;
    private String nome;
    private String e_mail;
    private String senha;
    private Date ano_nascimento;
    private String tipo;

    //*Esse metodo é o construtor da classe, qualquer instancia é criada com esses atributos*//
    public Usuario(String user_Id, Blob foto, String nome, String e_mail, String senha, Date ano_nascimento, String tipo) {
        this.user_Id = user_Id;
        this.foto = foto;
        this.nome = nome;
        this.e_mail = e_mail;
        this.senha = senha;
        this.ano_nascimento = ano_nascimento;
        this.tipo = tipo;
    }

    //*Esses são os get's e set's da classe, formas de atribuir e ler os valores de uma instância
    public String getUser_Id() {
        return user_Id;
    }

    public Blob getFoto() {
        return foto;
    }

    public String getNome() {
        return nome;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getSenha() {
        return senha;
    }

    public Date getAno_nascimento() {
        return ano_nascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAno_nascimento(Date ano_nascimento) {
        this.ano_nascimento = ano_nascimento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /*Esse metodo serve para converter um arquivo binario em uma arquivo Blob pro banco de dados*/
    public Blob converterBase64ParaBlob(String base64) throws Exception { byte[] bytes = Base64.getDecoder().decode(base64); return new SerialBlob(bytes); }
}


