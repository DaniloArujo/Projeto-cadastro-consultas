package medd.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import medd.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizaPaciente(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
