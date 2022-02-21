package sptech.projeto03.controller;

import org.springframework.web.bind.annotation.*;
import sptech.projeto03.entidade.Heroi;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    private List<Heroi> heroisList = new ArrayList<>();

    @GetMapping
    public List<Heroi> getHeroisList() {
        return heroisList;
    }

    @PostMapping
    public String cadastrarHeroi(@RequestBody Heroi novoHeroi){

        heroisList.add(novoHeroi);

        return "Herói cadastrado com sucesso.";

    }

    @GetMapping("/{indice}")
    public Heroi buscarHeroi(@PathVariable int indice){

        return indice > heroisList.size() ? null:heroisList.get(indice);

    }

    @DeleteMapping("/{indice}")
    public String deletarHeroi(@PathVariable int indice){

        if(indice <= heroisList.size()) {

            heroisList.remove(indice);

            return "Herói excluido com sucesso.";

        }else{

            return "Herói não encontrado.";

        }
    }

    @PutMapping("/{indice}")
    public String atualizarHeroi(@PathVariable int indice, @RequestBody Heroi novoHeroi){

        if(indice <= heroisList.size()) {

            heroisList.set(indice, novoHeroi);

            return "Heroi atualizado com sucesso.";

        }else{

            return "Herói não encontrado.";

        }
    }

}
