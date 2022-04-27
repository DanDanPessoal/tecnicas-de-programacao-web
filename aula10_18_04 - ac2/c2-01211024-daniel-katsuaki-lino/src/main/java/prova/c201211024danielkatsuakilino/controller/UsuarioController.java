package prova.c201211024danielkatsuakilino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prova.c201211024danielkatsuakilino.entity.UsuarioEntity;
import prova.c201211024danielkatsuakilino.repository.UsuarioRepository;
import prova.c201211024danielkatsuakilino.request.AutenticarUsuarioRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    private ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody @Valid UsuarioEntity user){

        repository.save(user);
        return ResponseEntity.status(201).build();

    }

    @PostMapping("/autenticacao")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticarUsuarioRequest usuarioBody){

        UsuarioEntity user = repository.findUsuarioEntityByUsuarioAndSenha(usuarioBody.getUsuario(), usuarioBody.getSenha());

        if(user == null){

            return ResponseEntity.status(404).build();

        }

        user.setAutenticar(true);
        repository.atualizarAutenticacao(true, user.getId());
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAllUsuarios(){

        return repository.findAll().isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(repository.findAll());

    }

    @DeleteMapping("/autenticacao/{usuario}")
    public ResponseEntity logoffUsuario(@PathVariable String usuario){

        UsuarioEntity user = repository.findUsuarioEntityByUsuario(usuario);


        if(user == null){

            return ResponseEntity.status(404).build();

        }

        if(user.isAutenticar()){

            repository.atualizarAutenticacao(false, user.getId());
            return ResponseEntity.status(200).build();

        }

        return ResponseEntity.status(406).build();
    }

    // Item f) da prova
    // Método que busca todos os usuários autenticados
    @GetMapping("/autenticados")
    public ResponseEntity<List<UsuarioEntity>> getAutenticados(){

        return repository.findAllByAutenticarEquals(true).isEmpty() ?
                ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(repository.findAllByAutenticarEquals(true));

    }
}
