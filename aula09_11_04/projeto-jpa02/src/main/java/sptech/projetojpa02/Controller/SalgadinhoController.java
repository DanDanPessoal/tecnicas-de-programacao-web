package sptech.projetojpa02.Controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa02.Repository.SalgadinhoRepository;
import sptech.projetojpa02.entity.Salgadinho;
import sptech.projetojpa02.requisicao.SalgadinhoPrecoApimentadoRequisicao;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

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

    @GetMapping("/apimentados")
    public ResponseEntity<List<Salgadinho>> getApimentados(){

        if(repository.findAllByApimentadoTrue().isEmpty()){

            return ResponseEntity.status(204).build();

        }

        return ResponseEntity.status(200).body(repository.findAllByApimentadoTrue());

    }

    @GetMapping("/contagem-nao-apimentados")
    public ResponseEntity<Integer> countApimentados(){

            return ResponseEntity.status(200).body(repository.countSalgadinhoByApimentadoFalse());

    }

    @GetMapping("/pouco-sal")
    public ResponseEntity<List<Salgadinho>> getPoucoSal(){

        if(repository.findAllByNivelSalIsLessThan(4).isEmpty()){

            return ResponseEntity.status(204).build();

        }

        return ResponseEntity.status(200).body(repository.findAllByNivelSalIsLessThan(4));
    }

    @GetMapping("/caros")
    public ResponseEntity<List<Salgadinho>> getCaros(){

        if(repository.findAllByPrecoGreaterThanEqual(20.0).isEmpty()){

            return ResponseEntity.status(204).build();

        }

        return ResponseEntity.status(200).body(repository.findAllByPrecoGreaterThanEqual(20.0));

    }

    @GetMapping({"/relatorio"})
    @ApiResponses({@ApiResponse(
            responseCode = "200",
            content = {@Content(
                    mediaType = "text/csv"
            )}
    )})
    public ResponseEntity getRelatorio() {
        List<Salgadinho> lista = this.repository.findAll();
        String relatorio = "";

        Salgadinho pet;
        for(Iterator var3 = lista.iterator(); var3.hasNext(); relatorio = relatorio + pet.getCodigo()
                + ";" + pet.getNome() + ";"+ pet.getNivelSal() + ";"+ pet.getPreco() + ";"+ pet.getApimentado() +"\n") {

            pet = (Salgadinho) var3.next();

        }

        return ((ResponseEntity.BodyBuilder)((ResponseEntity.BodyBuilder)
                ResponseEntity.status(200).header("content-type", new String[]{"text/csv"}))
                .header("content-disposition", new String[]{"filename=\"relatorio-de-pets.csv\""}))
                .body(relatorio);
    }

}
