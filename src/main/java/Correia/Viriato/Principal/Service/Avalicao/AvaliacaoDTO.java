package Correia.Viriato.Principal.Service.Avalicao;

import java.sql.Date;

public class AvaliacaoDTO {
    private String disciplina_id;
    private String aluno_id;
    private Date data_avaliacao;
    private int sociabilidade;       // corrigido
    private int participacao;
    private int responsabilidade;
    private int assiduidade;

    public AvaliacaoDTO() {}

    public AvaliacaoDTO(String disciplina_id, String aluno_id, Date data_avaliacao,
                        int sociabilidade, int participacao, int responsabilidade, int assiduidade) {
        this.disciplina_id = disciplina_id;
        this.aluno_id = aluno_id;
        this.data_avaliacao = data_avaliacao;
        this.sociabilidade = sociabilidade;
        this.participacao = participacao;
        this.responsabilidade = responsabilidade;
        this.assiduidade = assiduidade;
    }

    public String getDisciplina_id() {
        return disciplina_id;
    }

    public void setDisciplina_id(String disciplina_id) {
        this.disciplina_id = disciplina_id;
    }

    public String getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(String aluno_id) {
        this.aluno_id = aluno_id;
    }

    public Date getData_avaliacao() {
        return data_avaliacao;
    }

    public void setData_avaliacao(Date data_avaliacao) {
        this.data_avaliacao = data_avaliacao;
    }

    public int getSociabilidade() {
        return sociabilidade;
    }

    public void setSociabilidade(int sociabilidade) {
        this.sociabilidade = sociabilidade;
    }

    public int getParticipacao() {
        return participacao;
    }

    public void setParticipacao(int participacao) {
        this.participacao = participacao;
    }

    public int getResponsabilidade() {
        return responsabilidade;
    }

    public void setResponsabilidade(int responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public int getAssiduidade() {
        return assiduidade;
    }

    public void setAssiduidade(int assiduidade) {
        this.assiduidade = assiduidade;
    }
}
