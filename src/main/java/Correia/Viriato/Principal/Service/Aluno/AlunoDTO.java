package Correia.Viriato.Principal.Service.Aluno;

import Correia.Viriato.Principal.Service.Usuario.UsuarioDTO;
import java.util.List;
import java.util.Map;



// AlunoDTO herda de UsuarioDTO
public class AlunoDTO extends UsuarioDTO {
    private Map<String, Double> mediasPorDisciplina;
    private double mediaGeral;

    public Map<String, Double> getMediasPorDisciplina() {
        return mediasPorDisciplina;
    }

    public void setMediasPorDisciplina(Map<String, Double> mediasPorDisciplina) {
        this.mediasPorDisciplina = mediasPorDisciplina;
    }

    public double getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }
}
