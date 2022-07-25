package sptech.projetocontinuada03.servico;

import org.springframework.stereotype.Service;
import sptech.projetocontinuada03.entidade.Heroi;

@Service
public class HeroiService {

    /*
    O salário deve ser o salário base da classe do herói mais um bônus de 100,00 por ponto que ele tem.
    Caso o parâmetro "heroi" seja nulo, o método deve lancar uma IllegalArgumentException com a mensagem "Herói é obrigatório"
     */
    public double getSalario(Heroi heroi) {

        if(heroi == null){

            throw new IllegalArgumentException();

        }

        return heroi.getClasse().getSalarioBase() + (100.00 * heroi.getPontos());
    }

}
