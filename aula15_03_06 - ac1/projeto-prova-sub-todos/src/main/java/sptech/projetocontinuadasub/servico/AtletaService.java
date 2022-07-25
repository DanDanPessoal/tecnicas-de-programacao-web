package sptech.projetocontinuadasub.servico;

import org.springframework.stereotype.Service;
import sptech.projetocontinuadasub.entidade.Atleta;
import sptech.projetocontinuadasub.entidade.Modalidade;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtletaService {

    /*
    Crie uma quantidade suficiente de cenarios de teste para este metodo.

    O salário do Atleta recebido pode ser um desses valores:
    1. Caso o salario do atleta for entre os salario base e o salario maximo mais altos dentre suas modalidades, mantem o salario do atleta, ou seja, nao muda
    2. Caso contario, o salario passa a ser o maior salario base dentre suas modalidades

    Caso atleta for null ou modalidades for null ou uma lista vazia, lançe uma IllegalArgumentException com a mensagem "Informe o atleta e as modalidades"
     */
    public void setSalario(Atleta atleta, List<Modalidade> modalidades) {

        if(modalidades.isEmpty()){

            throw new IllegalArgumentException("Informe o atleta e as modalidades");

        }

        if(atleta == null){

            throw new IllegalArgumentException("Informe o atleta e as modalidades");

        }

        for(Modalidade modalidadeDaVez: modalidades){

            if(modalidadeDaVez == null){

                throw new IllegalArgumentException("Informe o atleta e as modalidades");

            }
        }

        // Este código está correto! Sem pegadinhas!
        List<Modalidade> modalidadesOrdenadas = modalidades.stream()
                .sorted(Comparator.comparing(Modalidade::getSalarioBase).reversed())
                .collect(Collectors.toList());
        Double maiorSalarioBase = modalidadesOrdenadas.get(0).getSalarioBase();
        Double maiorSalarioMaximo = modalidadesOrdenadas.get(0).getSalarioMaximo();
        // Este código está correto! Sem pegadinhas!

            if (!(atleta.getSalario() > maiorSalarioBase && atleta.getSalario() < maiorSalarioMaximo)) {

                atleta.setSalario(maiorSalarioBase);

            }
    }

}
