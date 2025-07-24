package medd.voll.api.domain.paciente;

import medd.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        String nome,

        String email,

        String telefone,

        String cpf,

        DadosEndereco endereco

) {

}
