package Correia.Viriato.Principal.Service.Turmas;

import java.util.List;

public record TurmaCompletaDTO(
        String turmaId,
        String nomeTurma,
        int capacidade,
        String turno,
        List<UsuarioResumoDTO> alunos,
        List<UsuarioResumoDTO> professores
) {}
