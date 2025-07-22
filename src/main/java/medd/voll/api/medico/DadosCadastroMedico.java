package medd.voll.api.medico;

import medd.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(

        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco

) {
}
