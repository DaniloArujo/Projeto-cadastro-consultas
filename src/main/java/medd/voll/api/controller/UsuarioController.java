package medd.voll.api.controller;

import medd.voll.api.usuario.DadosCadastroUsuario;
import medd.voll.api.usuario.Usuario;
import medd.voll.api.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroUsuario dados){
        repository.save(new Usuario(dados));
    }

}
