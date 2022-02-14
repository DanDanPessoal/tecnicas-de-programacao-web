package sptech.project01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlunoController {

    private String nomeEstudante = "vazio";
    private int nota1 = 123;
    private int nota2 = 123;

    // --------------------------------------------------------
    @GetMapping("/aluno/cadastrar-estudante/{nome}")
    public String cadastrarEstudante(@PathVariable String nome){

        nomeEstudante = nome;

        return "Estudante cadastrado com sucesso.";

    }

    // --------------------------------------------------------
    @GetMapping("/aluno/cadastrar-notas/{n1}/{n2}")
    public String cadastrarNotas(@PathVariable int n1, @PathVariable int n2){

        String msg;

        if(n1 > 10 || n1 < 0 || n2 > 10 || n2 < 0){

            msg = "Ambas notas devem estar entre 0 e 10";

        }else {

            nota1 = n1;
            nota2 = n2;

            msg = "Notas cadastradas com sucesso.";

        }

        return msg;
    }

    // --------------------------------------------------------
    @GetMapping("/aluno/resultado")
    public String resultado(){

        String msg;
        double media = (nota1+nota2)/2;

        if(nomeEstudante.equals("vazio") || nota1 == 123 || nota2 == 123){

            msg = "Cadastre o nome e as notas antes" + nomeEstudante + nota1 + nota2;

        }else if(media>=6){

            msg = String.format("Estudante aprovado com a média %.2f",media);

        }else{

            msg = String.format("Infelizmente a média %.2f, infelizmente não há apovação", media);

        }

        return msg;
    }

}
