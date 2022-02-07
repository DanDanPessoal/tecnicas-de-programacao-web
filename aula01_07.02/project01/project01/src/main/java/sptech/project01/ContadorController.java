package sptech.project01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContadorController {

    // --------------------------------------------------------
    // Essa chamada embaixo, comprova que a RestController é singleton,
    // Ou seja, quando definida, a instância vai ser sempre a mesma.
    // --------------------------------------------------------

    private int contador = 0;

    @GetMapping("/contador/contar")
    public String contar(){

        return String.format("Acessos: %d", contador++);

    }

    @GetMapping("/contador/zerar-contador")
    public String zerarContador() {

        contador = 0;

        return "Contador Zerado!";

    }

}
