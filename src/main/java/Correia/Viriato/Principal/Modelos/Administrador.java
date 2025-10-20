package Correia.Viriato.Principal.Modelos;

import java.sql.Blob;
import java.sql.Date;

public class Administrador extends Usuario{

    //* Essa Classe é responsavel por represertar os administradores no sistema, ela herda metodos e atributos de Usuario*//
    public Administrador(String user_Id, Blob foto, String nome, String e_mail, String senha, Date ano_nascimento, String tipo) {
        super(user_Id, foto, nome, e_mail, senha, ano_nascimento, tipo);
    }
}
