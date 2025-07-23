package medd.voll.api.usuario;

import jakarta.validation.constraints.NotNull;
import medd.voll.api.endereco.DadosEndereco;

public record DadosAtualizaUsuario(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
