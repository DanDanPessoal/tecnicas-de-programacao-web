package sptech.testeProva01.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.testeProva01.entidade.Usuario;
import sptech.testeProva01.repository.UsuarioRepository;
import sptech.testeProva01.request.RequestAutenticacao;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity postUser(
            @RequestBody @Valid Usuario user) {
        repository.save(user);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticacao")
    public ResponseEntity autenticacao(@RequestBody @Valid RequestAutenticacao autenticacao) {
        for (Usuario usuarioAtual : repository.findAll()) {
            if (usuarioAtual.autenticar(autenticacao.getLogin(), autenticacao.recuperaSenha())) {
                usuarioAtual.setAutenticado(true);
                repository.save(usuarioAtual);
                return ResponseEntity.status(201).body(usuarioAtual);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity allUsuarios() {
        if (repository.findAll().size() == 0) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @DeleteMapping("/autenticacao/{login}")
    public ResponseEntity logoffUsuario(@PathVariable String login) {
        for (Usuario usuarioAtual : repository.findAll()) {
            if (usuarioAtual.getLogin().equals(login)) {
                if (usuarioAtual.getAutenticado()) {
                    usuarioAtual.setAutenticado(false);
                    repository.save(usuarioAtual);
                    return ResponseEntity.status(200).build();
                } else {
                    return ResponseEntity.status(406).build();
                }
            }
        }
        return ResponseEntity.status(404).build();
    }


    //Endpoints extras
    @DeleteMapping("/autenticacao")
    public ResponseEntity logoffGeral() {
        for (Usuario usuarioAtual : repository.findAll()) {
            usuarioAtual.setAutenticado(false);
            repository.save(usuarioAtual);
        }
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/relatorio")
    public ResponseEntity getRelatorio() {
        List<Usuario> lista = repository.findAll();
        String relatorio = "";
        relatorio = "ID"+";"+"NOME" +";"+"LOGIN"+"\n";
        for (Usuario user : lista) {
            relatorio += user.getId()+";"+user.getNome()+";"+user.getLogin()+"\n";
        }
        return ResponseEntity.status(200)
                .header("content-type", "text/csv")
                .body(relatorio);
    }


}
