package medd.voll.api.usuario;

import medd.voll.api.endereco.DadosEndereco;
import medd.voll.api.endereco.Endereco;

public record DadosCadastroUsuario(

        String nome,

        String email,

        String telefone,

        String cpf,

        DadosEndereco endereco

) {

}
