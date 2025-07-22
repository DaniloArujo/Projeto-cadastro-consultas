package medd.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import medd.voll.api.endereco.DadosEndereco;

public record DadosAtualizarMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
