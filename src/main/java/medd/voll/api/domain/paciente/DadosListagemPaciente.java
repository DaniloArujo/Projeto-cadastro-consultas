package medd.voll.api.domain.paciente;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String email,
        String cpf

) {

    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf());
    }

}
