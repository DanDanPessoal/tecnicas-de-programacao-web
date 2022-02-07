package sptech.project01;

// Imports
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

// --------------------------------------------------------
// O @RestController avisa que a classe é uma rest, que pode ter chamadas da API.
// --------------------------------------------------------
@RestController
public class FrasesController {

    // --------------------------------------------------------
    // O @GetMapping transforma o método em uma chamada da API.
    // O que estiver dentro dos parenteses, representa sua URI
    // Sé não houver nada, vai ser o endereço raiz.
    // --------------------------------------------------------

    // https://localhost:8080/ ou https://localhost:8080, que dá na mesma.
    @GetMapping
    public String teste(){

        return "Digite /cumprimentar ou /despedida na url";

    }

    // https://localhost:8080/cumprimentar
    @GetMapping("/rest/cumprimentar")
    public String cumprimentar(){

        return "É nóis no REST!!!";

    }

    // https://localhost:8080/despedida
    @GetMapping("/rest/despedida")
    public String despedida(){

        return "Boa Noite";

    }

    // https://localhost:8080/sorteio
    @GetMapping("/rest/sorteio")
    public String sorteio(){

        Integer numero = ThreadLocalRandom.current().nextInt(0, 100);

        return "Seu número sorteado foi: "+ numero;

    }

    @GetMapping("/rest/sorteio2")
    public String sorteio2(){

        Double numero = Math.random()*100;

        return String.format("Seu número sorteado foi %.0f", numero);

    }

}
