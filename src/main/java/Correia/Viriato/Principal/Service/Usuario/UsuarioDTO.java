package Correia.Viriato.Principal.Service.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class UsuarioDTO {

    private String user_id;
    private String nome;
    private String e_mail;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // senha não será enviada no JSON
    private String senha;

    private Date ano_nascimento;
    private String tipo;
    private String foto; // base64 no JSON
    private int idade;

    // Dados adicionais (opcionais)
    private String matricula;
    private String telefone_aluno;
    private String telefone_responsavel;
    private String numeroContrato;
    private String telefone;
    private String formacao;
    private Double salario;
    private String categoria;
    private String setor;

    // Getters e Setters
    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getE_mail() { return e_mail; }
    public void setE_mail(String e_mail) { this.e_mail = e_mail; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Date getAno_nascimento() { return ano_nascimento; }
    public void setAno_nascimento(Date ano_nascimento) {
        this.ano_nascimento = ano_nascimento;
        calcularIdade();
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Métodos auxiliares
    public void calcularIdade() {
        if (ano_nascimento != null) {
            LocalDate nascimento = ano_nascimento.toLocalDate();
            this.idade = Period.between(nascimento, LocalDate.now()).getYears();
        }
    }

    // Getters e setters adicionais
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getTelefone_aluno() { return telefone_aluno; }
    public void setTelefone_aluno(String telefone_aluno) { this.telefone_aluno = telefone_aluno; }

    public String getTelefone_responsavel() { return telefone_responsavel; }
    public void setTelefone_responsavel(String telefone_responsavel) { this.telefone_responsavel = telefone_responsavel; }

    public String getNumeroContrato() { return numeroContrato; }
    public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getFormacao() { return formacao; }
    public void setFormacao(String formacao) { this.formacao = formacao; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }
}