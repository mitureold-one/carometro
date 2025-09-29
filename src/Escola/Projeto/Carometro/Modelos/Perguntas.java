package Escola.Projeto.Carometro.Modelos;

import java.util.List;

public class Perguntas {
    private String enunciado;
    private Disciplina disciplina;
    private List<String> alternativas;
    private byte respotacorreta;
    private byte resposta;
    private boolean pontuacao;
    private Professor autor;

    public Perguntas(String enunciado, Disciplina disciplina, List<String> alternativas, byte respotacorreta, Professor autor) {
        this.enunciado = enunciado;
        this.disciplina = disciplina;
        this.alternativas = alternativas;
        this.respotacorreta = respotacorreta;
        this.autor = autor;
    }
    public void corrigir(byte resposta,byte respotacorreta) {
           if(this.respotacorreta == this.resposta) {
               pontuacao = true;
           }else{
               pontuacao = false;
           };
    }
    public void setalternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }


    @Override
    public String toString() {
        return "enunciado= " + enunciado +
                "disciplina= " + disciplina +
                "alternativas= " + alternativas +
                "respotacorreta = " + respotacorreta +
                "autor= " + autor;
    }
}
