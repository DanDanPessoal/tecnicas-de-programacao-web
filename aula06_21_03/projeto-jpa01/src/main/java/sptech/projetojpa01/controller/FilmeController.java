package sptech.projetojpa01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa01.entity.Filme;
import sptech.projetojpa01.repository.IFilme;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private IFilme repository;

    @GetMapping
    public ResponseEntity getFilmes(){

        // o .findAll() faz um select * no banco
        List<Filme> filmes = repository.findAll();

        if(filmes.isEmpty()){

            return ResponseEntity.status(204).build();

        }

        return ResponseEntity.status(200).body(filmes);

    }

    @PostMapping
    public ResponseEntity addFilmes(@RequestBody Filme F){

        // o .save() faz insert no banco.
        repository.save(F);

        return ResponseEntity.status(201).build();

    }

    @GetMapping("/{nomeFilme}")
    public ResponseEntity getFilmeByName(@PathVariable String nomeFilme){

        List<Filme> filmes = repository.findAll();

        for(Filme f:filmes){

            if(f.getNome().equalsIgnoreCase(nomeFilme)){

                return ResponseEntity.status(200).body(f);

            }

        }

        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity getFilmeById(@PathVariable int codigo){

        // O ResponseEntity.of() recebe um Optional e retorna
        // status 200 e com o valor no corpo se tiver valor
        // status 404 e sem corpo se nao tiver valor
        return ResponseEntity.of(repository.findById(codigo));

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity deleteFilmeById(@PathVariable int codigo){

        if(repository.existsById(codigo)){

            repository.deleteById(codigo);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity atualizarFilme(@PathVariable int codigo, @RequestBody Filme f){

        if(repository.existsById(codigo)){

            f.setCodigo(codigo);

            repository.save(f);

            return ResponseEntity.status(200).body(repository.getById(codigo));


        }

        return ResponseEntity.status(404).build();

    }
}
