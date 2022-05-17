package sptech.exercicio01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.exercicio01.entity.Carro;
import sptech.exercicio01.repository.CarroRepository;
import sptech.exercicio01.resposta.ModeloCarroResposta;

import java.util.List;

@RestController
@RequestMapping("carros")
public class CarroController {

    @Autowired
    private CarroRepository repository;

    @GetMapping
    public ResponseEntity<List<Carro>> getAllCarros(){

        return repository.findAll().isEmpty() ? ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(repository.findAll());

    }

    @GetMapping("/filtro/fabricante/{idFabricante}")
    public ResponseEntity<List<Carro>> getCarrosByIdFabricante(@PathVariable Integer idFabricante){

        return repository.findByFabricanteCodigoEquals(idFabricante).isEmpty() ?
                ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(repository.findByFabricanteCodigoEquals(idFabricante));

    }

    @GetMapping("/contagem/fabricante/{idFabricante}")
    public ResponseEntity<Integer> getQuantidadeCarrosByIdFabricante(@PathVariable Integer idFabricante){

        return ResponseEntity.status(200).body(repository.countByFabricanteCodigoEquals(idFabricante));

    }

    @GetMapping("/contagem/pais/{nomePais}")
    public ResponseEntity<Integer> getQuantidadeCarrosByNomePais(@PathVariable String nomePais){

        return ResponseEntity.status(200).body(repository.countByFabricantePaisNomeEquals(nomePais));

    }

    @GetMapping("/simples/{modeloCarro}")
    public ResponseEntity<List<ModeloCarroResposta>> getModeloResposta(@PathVariable String modeloCarro){

        return  repository.getModeloCarro(modeloCarro).isEmpty() ?
                ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(repository.getModeloCarro(modeloCarro));

    }

}
