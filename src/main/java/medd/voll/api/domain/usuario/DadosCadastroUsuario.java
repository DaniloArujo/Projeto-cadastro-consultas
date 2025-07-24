package medd.voll.api.domain.usuario;

import medd.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroUsuario(

        String nome,

        String email,

        String telefone,

        String cpf,

        DadosEndereco endereco

) {

}
