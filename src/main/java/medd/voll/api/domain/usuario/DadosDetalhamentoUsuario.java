package medd.voll.api.domain.usuario;

import medd.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoUsuario(

        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco

) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf(), usuario.getTelefone(), usuario.getEndereco());
    }

}
