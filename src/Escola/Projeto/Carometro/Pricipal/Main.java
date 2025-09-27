package Escola.Projeto.Carometro.Pricipal;

import Escola.Projeto.Carometro.Modelos.Alunos;

public class Main {
    public static void main(String[] args) {
        Alunos Novoaluno = new Alunos("Al-001","Naruto","2004-05-13","narutinho@gmail.com","Sasuke","(99) 9xxxx-xxxx");

        System.out.println("Novo aluno: " + Novoaluno);
    }

}
