package Correia.Viriato.Principal.Service.Turmas;

public record UsuarioResumoDTO(
        String userId,
        String nome,
        String email,
        String foto // Base64
) {}
