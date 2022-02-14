package sptech.projeto02;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {

    Pais teste = new Pais();

//    List<Pais> paises = List.of(
//
//            new Pais("Brasil", 15, 10, 20),
//            new Pais("Japão", 101, 200, 20),
//            new Pais("Alemanha", 50, 10, 30)
//
//
//    );

    List<Pais> paises = new ArrayList<>();

    @GetMapping
    public Pais getPais(){

        teste.setMedalhasBronze(10);
        teste.setMedalhasOuro(2);
        return teste;

    }

    @GetMapping("/cadastrar/{nome}")
    public String cadastrarPais(@PathVariable String nome){

        paises.add(new Pais(nome, 0, 0, 0));
        return String.format("Pais %s Cadastrado com sucesso", nome);

    }

    @GetMapping("/buscar/{id}")
    public Pais buscarPaisById(@PathVariable int id){

        return paises.get(id);

    }

    @GetMapping("/listar")
    public List<Pais> getTodosPaises(){

        return paises;

    }
    
}
