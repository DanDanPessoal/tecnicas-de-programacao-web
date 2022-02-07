package sptech.project01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculosController {

    // --------------------------------------------------------
    // Você consegue passar valores pela URI, que são chamados de Path Params
    // Você tem que passar esses parametros entre chaves "{}"
    // E recebe esses valores pelos Parâmetros, utilizando o @PathVariable
    // OBS: Os nomes dos parâmetros devem ser os mesmos da URI
    // --------------------------------------------------------

   @GetMapping("/calculos/somar/{n1}/{n2}")
   public String somar(@PathVariable double n1, @PathVariable double n2){

       double soma = n1+n2;
       return String.format("A soma entre %.2f e %.2f é %.2f", n1, n2, (n1+n2));

   }


}
