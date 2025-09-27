package Escola.Projeto.Carometro.Pricipal;

import Escola.Projeto.Carometro.Modelos.Alunos;
import Escola.Projeto.Carometro.Modelos.Professor;
import Escola.Projeto.Carometro.Modelos.Turmas;

public class Main {
    public static void main(String[] args) {
        Alunos aluno = new Alunos("Naruto","2004-05-13","narutinho@gmail.com","Sasuke","(99) 9xxxx-xxxx");
        Alunos Novoaluno = new Alunos("Sasuke","2005-10-06","vingador@gmail.com","nartuinho","(99) 9xxxx-xxxx");
        Professor professor = new Professor("Hemilly", "2003-09-06","hemilly@gmail.com","hemilly","(99)9xxxx-xxxx");
        Professor Novaprofessor = new Professor("Caio","1990-10-05","caio@gmail.com","caio","(99)9xxxx-xxxx");

        Turmas turma = new Turmas("TM-001","1°ano A");
        turma.addAlunos(aluno);
        turma.addAlunos(Novoaluno);

        System.out.println("Nome 01| " + Novoaluno.getNome().toUpperCase());
        System.out.println("Nome 01| " + Novoaluno.getAnonascimeto().toUpperCase());
        System.out.println("Nome 01| " + Novoaluno.getTurmas());

        System.out.println("Nome 02| " + aluno.getNome().toUpperCase());
        System.out.println("Nome 02| " + aluno.getAnonascimeto().toUpperCase());
        System.out.println("Nome 02| " + aluno.getTurmas());


        System.out.println("Na turma do "+ turma.getNome() + " estão matriculados os alunos"+ turma.listarAlunos());


    }

}
