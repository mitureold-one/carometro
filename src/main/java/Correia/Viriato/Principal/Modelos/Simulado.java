package Correia.Viriato.Principal.Modelos;

/*public class Simulado {
    private List<Perguntas> perguntas = new ArrayList<>();

    public void adicionarPergunta(Perguntas pergunta) {
        // Contagem por disciplina. Se Disciplina tiver getNome(), use-o; caso contrário compara referência.
        long count = perguntas.stream()
                .filter(p -> {
                    if (p.getDisciplina() == null || pergunta.getDisciplina() == null) return false;
                    // preferível comparar por nome para não depender de referências:
                    return p.getDisciplina().getNome().equals(pergunta.getDisciplina().getNome());
                })
                .count();

        if (count < 5) {
            perguntas.add(pergunta);
        } else {
            System.out.println("⚠ Não é possível adicionar mais de 5 perguntas da disciplina: "
                    + pergunta.getDisciplina().getNome());
        }
    }

    public List<Perguntas> getSimulado() {
        return perguntas;
    }

    public int calcularNota() {
        int nota = 0; // calcula localmente (não acumula entre chamadas)
        for (Perguntas p : perguntas) {
            if (p.isPontuacao()) {
                nota++;
            }
        }
        return nota;
    }
}

 */


