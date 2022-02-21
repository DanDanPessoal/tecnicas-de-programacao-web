package sptech.exercicio1.controller;

import org.springframework.web.bind.annotation.*;
import sptech.exercicio1.entidade.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    List<Pokemon> pokemonList = new ArrayList<>();

    // ----------------------------------------------------------
    @GetMapping("/cadastrar/{nome}/{tipo}/{forca}/{capturado}")
    public String cadastrarPokemon(@PathVariabl e String nome, @PathVariable String tipo,
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
    public String removerPokemon(@PathVariable int indice){

        String msg;

        if(indice > pokemonList.size()){

            msg = "Indice inexistente";

        }else{

            msg = "Pokemon "+ pokemonList.get(indice).getNome() + " removido com sucesso.";
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

            // Assim funcionava também.
            // pokemonList.set(indice, new Pokemon(nome, tipo, forca, foiCapturado));

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
    // Método feito de forma clássica
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
    // Método melhorado da contagem.
    @GetMapping("{tipo}/contagem2")
    public String listarQuantidadeTipoPokemon2(@PathVariable String tipo){

        Long contagem = pokemonList.stream().filter(pokemon -> pokemon.getTipo().equals(tipo)).count();

        return "Existem " + contagem + " pokemons do tipo "+ tipo + " cadastrados";

    }

    // ----------------------------------------------------------
    // Método feito de forma clássica
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
    // Método melhorado dos capturados.
    @GetMapping("/capturados2")
    public List<Pokemon> listarPokemonsCapturador2(){

        return pokemonList.stream().filter(pokemon -> pokemon.getCapturado()).collect(Collectors.toList());

    }

    // ----------------------------------------------------------
    // Método feito de forma clássica
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
    // Método melhorado dos fortes;
    @GetMapping("/fortes2")
    public List<Pokemon> listarPokemonFortes2(){

        return pokemonList.stream().filter(pokemon -> pokemon.getForca()>3000).collect(Collectors.toList());

    }

    // ----------------------------------------------------------
    // Método feito de forma clássica
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

    // ----------------------------------------------------------
    // Método melhorado dos fracos.
    @GetMapping("/fracos2")
    public List<Pokemon> listarPokemonFracos2(){

        return pokemonList.stream().filter(pokemon -> pokemon.getForca()<=3000).collect(Collectors.toList());

    }
    
}
