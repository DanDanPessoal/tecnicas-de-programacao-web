package sptech.projeto02;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

    @GetMapping
    public String getCidades(){

        return "Listagem de todas as cidades do Brasil.";

    }

    @GetMapping("/{nome}")
    public String getCidadeByName(@PathVariable String nome){

        return "retornaria a cidade: "+ nome;

    }

    @GetMapping("/cadastrar")
    public String cadastrarCidades(@PathVariable String nome){

        return "";

    }

    @GetMapping("/remover/{id}")
    public String removerCidade(@PathVariable int id){

        return "Removendo a cidade com id "+ id;

    }

    @PostMapping("/post")
    public String testePost(){

        return "kkkk";

    }
}
