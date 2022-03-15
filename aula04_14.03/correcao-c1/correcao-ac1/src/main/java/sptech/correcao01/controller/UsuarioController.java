package sptech.correcao01.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.correcao01.entity.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public ResponseEntity postUsuario(@RequestBody Usuario novoUsuario) {

        usuarios.add(novoUsuario);
        // return String.format("Usuário %s cadastrado no sistema", novoUsuario.getNome());

        // Responde o 201 (Created), Conseguiu Criar o usuário
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticacao/{usuario}/{senha}")
    public ResponseEntity logonUsuario(@PathVariable String usuario, @PathVariable String senha) {

        for (Usuario usuarioAtual : usuarios) {

            if (usuarioAtual.autenticar(usuario, senha)) {

                // return String.format("Usuário %s agora está autenticado", usuarioAtual.getNome());

                // Resposta 200 (OK), conseguiu autenticar o usuário
                return ResponseEntity.status(200).build();

            }
        }

        // return String.format("Usuário %s não encontrado", usuario);

        // Resposta 401 (Unathorized), não tem autorização para autenticar o usuário.
        return ResponseEntity.status(401).build();
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public ResponseEntity logoffUsuario(@PathVariable String usuario) {

        for (Usuario usuarioAtual : usuarios) {

            if (usuarioAtual.getUsuario().equals(usuario)) {

                if (usuarioAtual.isAutenticado()) {

                    usuarioAtual.setAutenticado(false);
                    // return String.format("Logoff do usuário %s concluído", usuarioAtual.getNome());

                    // Resposta 200 (OK), conseguiu deslogar o usuário.
                    return ResponseEntity.status(200).build();

                } else {

                    // return String.format("Usuário %s NÃO está autenticado", usuarioAtual.getNome());

                    // Resposta 403 (Forbidden), O usuário não corresponde a o esperado.
                    return ResponseEntity.status(403).build();
                }
            }
        }

        // return String.format("Usuário %s não encontrado", usuario);

        // Resposta 404 (Not Found), não achou o usuário para desloga-lo.
        return ResponseEntity.status(404).build();
    }

    // EndPoints extras:

    @GetMapping("/autenticados")
    public ResponseEntity<List<Usuario>> getUsuariosAutenticados() {

        // Resposta 200 (OK), conseguiu retornar os usuários autenticados.
        // Resposta 204 (Sem conteudo), conseguiu realizar a operação, mas não tem conteudo.
        return usuarios.stream()
                .filter(Usuario::isAutenticado)
                .collect(Collectors.toList()).isEmpty() ? ResponseEntity.status(204).build():
                ResponseEntity.status(200).body(usuarios.stream()
                .filter(Usuario::isAutenticado)
                .collect(Collectors.toList()));

    }

    @DeleteMapping("/autenticacao")
    public ResponseEntity logoffGeral() {

        for (Usuario usuario : usuarios) {
            usuario.setAutenticado(false);
        }

        // return "Todos os usuarios sofreram logoff!";
        return ResponseEntity.status(200).body("Todos os usuarios sofreram logoff!");
    }

    @GetMapping("/relatorio")
    public ResponseEntity<String> getRelatorio() {

        long autenticados = usuarios.stream()
                                    .filter(Usuario::isAutenticado)
                                    .count();

        // Resposta 200 (OK), conseguiu contar os autenticados
        return ResponseEntity.status(200).body(String.format(
                "Total de usuários: %s. Autenticados: %d. Não autenticados: %s",
                usuarios.size(), autenticados, usuarios.size() - autenticados));
    }
}

