package sptech.exercicios;

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

        if((vitoria+empate+derrota) == 0 ){

            return "Cadastre pelo menos uma vitória, derrota ou empate.";

        }else{

            double pontuacaoMax = (vitoria + empate + derrota)*3;
            Integer pontuacao = (vitoria*3) + empate;

            String msg = String.format("Você tem %d pontos em %d partidas. <br>" +
                            "%.2f%% de aproveitamento", pontuacao, (vitoria + empate + derrota),
                    ((pontuacao/pontuacaoMax)*100));

            return msg;

        }
    }
}