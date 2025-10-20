package Correia.Viriato.Principal.Modelos;

/*public class Perguntas {
    private String enunciado;
    private Disciplina disciplina;
    private List<String> alternativas;
    private byte respotacorreta; // índice da alternativa correta (0-based)
    private byte resposta;       // resposta do aluno (0-based)
    private boolean pontuacao;   // true se acertou
    private Professor autor;

    public Perguntas(String enunciado,Disciplina disciplina, List<String> alternativas, byte respotacorreta, Professor autor) {
        this.enunciado = enunciado;
        this.disciplina = disciplina;
        this.alternativas = alternativas;
        this.respotacorreta = respotacorreta;
        this.autor = autor;
        this.pontuacao = false;
    }

    // corrige a pergunta com a resposta do aluno
    public void corrigir(byte resposta) {
        this.resposta = resposta;
        this.pontuacao = (resposta == this.respotacorreta);
    }

    // getter necessário para o Simulado
    public boolean isPontuacao() {
        return pontuacao;
    }

    // getter necessário para contar por disciplina no Simulado
    public Disciplina getDisciplina() {
        return disciplina;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disciplina: ").append(disciplina).append("\n");
        sb.append("Autor: ").append(autor).append("\n");
        sb.append("Pergunta: ").append(enunciado).append("\n");
        for (int i = 0; i < alternativas.size(); i++) {
            sb.append((i + 1)).append(") ").append(alternativas.get(i)).append("\n");
        }
        return sb.toString();
    }
}
 */

