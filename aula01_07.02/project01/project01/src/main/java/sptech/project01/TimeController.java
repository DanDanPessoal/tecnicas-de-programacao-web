package sptech.project01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    private int vitoria = 0;
    private int empate = 0;
    private int derrota = 0;

    @GetMapping("/time/cadastrar-vitoria")
    public String vitoria(){

        vitoria++;

        return "Vitória cadastrada com sucesso";

    }

    @GetMapping("/time/cadastrar-empate")
    public String empate(){

        empate++;

        return "Empate cadastrado com sucesso";

    }

    @GetMapping("/time/cadastrar-derrota")
    public String derrota(){

        derrota++;

        return "Derrota cadastrada com sucesso";

    }

    @GetMapping("/time/pontuacao")
    public String pontuacao(){

        int pontuacao = (vitoria*3) + empate;
        return "Você tem "+ pontuacao + " em " + (vitoria + empate + derrota) + " partidas";

    }

}
