package medd.voll.api.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medd.voll.api.endereco.Endereco;

@Table(name="usuarios")
@Entity(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private  String email;

    private  String telefone;

    private  String cpf;

    @Embedded
    private  Endereco endereco;

    private Boolean ativo;

    public Usuario(DadosCadastroUsuario dados) {

        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;

    }
}
