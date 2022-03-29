package sptech.projetojpa02.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa02.Repository.SalgadinhoRepository;
import sptech.projetojpa02.entity.Salgadinho;
import sptech.projetojpa02.requisicao.SalgadinhoPrecoApimentadoRequisicao;

import javax.validation.Valid;

@RestController
@RequestMapping("/salgadinhos")
public class SalgadinhoController {

    @Autowired
    private SalgadinhoRepository repository;

    @PostMapping
    public ResponseEntity postSalgadinho(@RequestBody @Valid Salgadinho novoSalgado){

        repository.save(novoSalgado);

        return ResponseEntity.status(201).build();

    }

    @GetMapping
    public ResponseEntity getSalgadinho(){

        return ResponseEntity.status(200).body(repository.findAll());

    }

    @GetMapping("/simples")
    public ResponseEntity getSalgadinhoSimples(){

        return ResponseEntity.status(200).body(repository.listaSimples());

    }

    @GetMapping("/contagem")
    public ResponseEntity getContagem(){

        long contagem = repository.count();
        return ResponseEntity.status(200).body(contagem);

    }

    @PatchMapping("/{codigo}/preco/{preco}")
    public ResponseEntity patchSalgadinho(@PathVariable int codigo, @PathVariable Double preco){

        if(repository.existsById(codigo)){

            repository.atualizarPreco(codigo, preco);

            return ResponseEntity.status(200).build();

        }else{

            return ResponseEntity.status(404).build();

        }
    }

    @PatchMapping("/{codigo}/preco-apimentado")
    public ResponseEntity patchSalgadinhoPrecoApimentado(@PathVariable Integer codigo,
                                                         @RequestBody SalgadinhoPrecoApimentadoRequisicao requisicao){

        if(repository.existsById(codigo)){

            repository.atualizarPrecoApimentado(codigo, requisicao.getPreco(), requisicao.getApimentado());
            return ResponseEntity.status(200).build();

        }

        return ResponseEntity.status(404).build();

    }
}
