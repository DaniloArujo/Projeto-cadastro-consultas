package medd.voll.api.endereco;

public record DadosEndereco(

        String logradouro,
        String bairro,
        String cep,
        String cidade,
        String numero,
        String complemento,
        String uf

) {
}
