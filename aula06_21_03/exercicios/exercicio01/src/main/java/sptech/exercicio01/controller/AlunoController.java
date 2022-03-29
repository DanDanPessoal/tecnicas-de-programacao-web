package sptech.exercicio01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.exercicio01.entity.Aluno;
import sptech.exercicio01.repository.IAluno;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    private IAluno repository;

    @PostMapping
    public ResponseEntity cadastrarAluno(@RequestBody Aluno a){

        repository.save(a);

        return ResponseEntity.status(200).build();

    }

    @GetMapping("/{codigo}")
    public ResponseEntity getAlunoById(@PathVariable int codigo){

        return ResponseEntity.of(repository.findById(codigo));

    }

    @GetMapping
    public ResponseEntity getAlunos(){

        if(repository.findAll().isEmpty()){

            return ResponseEntity.status(204).build();

        }

        return ResponseEntity.status(200).body(repository.findAll());

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity deleteAlunoById(@PathVariable int codigo){

        if(repository.existsById(codigo)){

            repository.deleteById(codigo);
            return ResponseEntity.status(200).build();

        }

        return ResponseEntity.status(404).build();
    }
}
