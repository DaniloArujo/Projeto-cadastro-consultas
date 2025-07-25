package medd.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import medd.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizarMedico(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

) {
}
