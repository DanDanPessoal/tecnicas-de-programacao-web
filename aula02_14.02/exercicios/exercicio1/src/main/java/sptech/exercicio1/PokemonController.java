package sptech.exercicio1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    List<Pokemon> pokemonList = new ArrayList<>();

    // ----------------------------------------------------------
    @GetMapping("/cadastrar/{nome}/{tipo}/{forca}/{capturado}")
    public String cadastrarPokemon(@PathVariable String nome, @PathVariable String tipo,
                                   @PathVariable Double forca, @PathVariable String capturado){

        Boolean foiCapturado;

        if(!capturado.equalsIgnoreCase("Sim") && !capturado.equalsIgnoreCase("nao")){

            return "Digite sim ou nao no final da URI para falar se foi capturado ou não";

        }else if(capturado.equalsIgnoreCase("Sim")){

            foiCapturado = true;

        }else{

            foiCapturado = false;

        }

        pokemonList.add(new Pokemon(nome, tipo, forca, foiCapturado));

        return "Pokemon "+ nome + " cadastrado com sucesso";

    }

    // ----------------------------------------------------------
    @GetMapping("/remover/{indice}")
    public String removerPokemon(@PathVariable Integer indice){

        String msg;

        if(indice > pokemonList.size()){

            msg = "Indice inexistente";

        }else{

            msg = "Pokemon "+ pokemonList.get(indice).getNome();
            pokemonList.remove(indice);

        }

        return msg;
    }

    // ----------------------------------------------------------
    @GetMapping("/buscar/{indice}")
    public Pokemon buscarPokemon(@PathVariable Integer indice){

        if(pokemonList.get(indice) == null){

            return null;

        }else{

            return pokemonList.get(indice);
        }
    }

    // ----------------------------------------------------------
    @GetMapping("/atualizar/{indice}/{nome}/{tipo}/{forca}/{capturado}")
    public String atualizarPokemon(@PathVariable Integer indice, @PathVariable String nome,
                                   @PathVariable String tipo, @PathVariable Double forca,
                                   @PathVariable String capturado){

        String msg;

        if(indice > pokemonList.size()){

            msg = "Indice inexistente";

        }else{

            Boolean foiCapturado;

            if(!capturado.equalsIgnoreCase("Sim") && !capturado.equalsIgnoreCase("nao")){

                return "Digite sim ou nao no final da URI para falar se foi capturado ou não";

            }else if(capturado.equalsIgnoreCase("Sim")){

                foiCapturado = true;

            }else{

                foiCapturado = false;

            }

            pokemonList.get(indice).setNome(nome);
            pokemonList.get(indice).setTipo(tipo);
            pokemonList.get(indice).setForca(forca);
            pokemonList.get(indice).setCapturado(foiCapturado);

            msg = "Pokemon "+ nome + " Atualizado com sucesso";

        }

        return msg;
    }

    // ----------------------------------------------------------
    @GetMapping("/listar")
    public List<Pokemon> listarPokemons(){

        return pokemonList;

    }

    // ----------------------------------------------------------
    @GetMapping("{tipo}/contagem")
    public String listarQuantidadeTipoPokemon(@PathVariable String tipo){

        Integer contagem = 0;

        for(Pokemon pokemonDaVez: pokemonList) {
            if (tipo.equalsIgnoreCase(pokemonDaVez.getTipo())){

                contagem++;

            }
        }

        return "Existem " + contagem + " pokemons do tipo "+ tipo + " cadastrados";
    }

    // ----------------------------------------------------------
    @GetMapping("/capturados")
    public List<Pokemon> listarPokemonsCapturador(){

        List<Pokemon> pokemonsCapturados = new ArrayList<>();

        for(Pokemon pokemonDaVez: pokemonList){

            if(pokemonDaVez.getCapturado()){

                pokemonsCapturados.add(pokemonDaVez);

            }
        }

        return pokemonsCapturados;
    }

    // ----------------------------------------------------------
    @GetMapping("/fortes")
    public List<Pokemon> listarPokemonFortes(){

        List<Pokemon> pokemonsFortes = new ArrayList<>();

        for(Pokemon pokemonDaVez: pokemonList){

            if(pokemonDaVez.getForca() > 3000){

                pokemonsFortes.add(pokemonDaVez);

            }
        }

        return pokemonsFortes;
    }

    // ----------------------------------------------------------
    @GetMapping("/fracos")
    public List<Pokemon> listarPokemonFracos(){

        List<Pokemon> pokemonsFracos = new ArrayList<>();

        for(Pokemon pokemonDaVez: pokemonList){

            if(pokemonDaVez.getForca() <= 3000){

                pokemonsFracos.add(pokemonDaVez);

            }
        }

        return pokemonsFracos;
    }
}
