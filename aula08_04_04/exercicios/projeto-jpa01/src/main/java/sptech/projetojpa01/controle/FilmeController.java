package sptech.projetojpa01.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa01.entidade.Filme;
import sptech.projetojpa01.repositorio.FilmeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public ResponseEntity getFilmes() {
        // o findAll() pega todos os registros da tabela
        List<Filme> filmes = repository.findAll();
        if (filmes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(filmes);
    }

    @PostMapping
    public ResponseEntity postFilme(@RequestBody @Valid Filme novoFilme) {
        // o .save() faz o insert no banco
        repository.save(novoFilme);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity getFilme(@PathVariable int codigo) {
/*
O ResponseEntity.of() recebe um Optional e retorna...
- status 404 e sem corpo se não tiver valor
- status 200 e com o valor no corpo se tiver valor
         */
// O findById() tenta recuperar 1 registro do banco
        return ResponseEntity.of(repository.findById(codigo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity deleteFilme(@PathVariable int codigo) {
        if (repository.existsById(codigo)) {
            repository.deleteById(codigo);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity putFilme(@PathVariable int codigo,
                                   @RequestBody Filme filme) {
        if (repository.existsById(codigo)) {
            filme.setCodigo(codigo);
            repository.save(filme);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
