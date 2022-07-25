package sptech.projetocontinuada03.servico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sptech.projetocontinuada03.entidade.ClasseHeroi;
import sptech.projetocontinuada03.entidade.Heroi;

import static org.junit.jupiter.api.Assertions.*;

class HeroiServiceTest {

    HeroiService heroiService = new HeroiService();
    ClasseHeroi classeHeroi = new ClasseHeroi();
    Heroi heroi = new Heroi();

    @Test
    @DisplayName("Metodo getSalario() deve dar um exception se Heroi for nulo")
    void getSalario_heroiInvalidoRetornarException() {

        Heroi heroi = null;

        assertThrows(IllegalArgumentException.class, () ->{

            heroiService.getSalario(heroi);

        });
    }

    @Test
    @DisplayName("Metodo getSalario() deve trazer o salario correto para classe S")
    void getSalario_heroiValidoRetornarSalarioCorretoParaClasseS(){

        Double salarioBaseClasseS = 200000.0;

        Integer pontosClasseS = 10500;

        Double novoSalarioClasseS = salarioBaseClasseS + (100.00 * pontosClasseS);

        classeHeroi.setSalarioBase(salarioBaseClasseS);

        heroi.setPontos(pontosClasseS);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseS, heroiService.getSalario(heroi));

        Integer pontosClasseS2 = 20321;
        Double novoSalarioClasseS2 = salarioBaseClasseS + (100.00 * pontosClasseS2);

        classeHeroi.setSalarioBase(salarioBaseClasseS);

        heroi.setPontos(pontosClasseS2);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseS2, heroiService.getSalario(heroi));

    }

    @Test
    @DisplayName("Metodo getSalario() deve trazer o salario correto para classe A")
    void getSalario_heroiValidoRetornarSalarioCorretoParaClasseA(){

        Double salarioBaseClasseA = 150000.0;

        Integer pontosClasseA = 9500;
        Double novoSalarioClasseA = salarioBaseClasseA + (100.00 * pontosClasseA);

        classeHeroi.setSalarioBase(salarioBaseClasseA);

        heroi.setPontos(pontosClasseA);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseA, heroiService.getSalario(heroi));

        Integer pontosClasseA2 = 9950;
        Double novoSalarioClasseA2 = salarioBaseClasseA + (100.00 * pontosClasseA2);

        classeHeroi.setSalarioBase(salarioBaseClasseA);

        heroi.setPontos(pontosClasseA2);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseA2, heroiService.getSalario(heroi));



    }

    @Test
    @DisplayName("Metodo getSalario() deve trazer o salario correto para classe B")
    void getSalario_heroiValidoRetornarSalarioCorretoParaClasseB(){

        Double salarioBaseClasseB = 80000.0;

        Integer pontosClasseB = 5500;

        Double novoSalarioClasseB = salarioBaseClasseB + (100.00 * pontosClasseB);

        classeHeroi.setSalarioBase(salarioBaseClasseB);

        heroi.setPontos(pontosClasseB);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseB, heroiService.getSalario(heroi));

        Integer pontosClasseB2 = 8959;

        Double novoSalarioClasseB2 = salarioBaseClasseB + (100.00 * pontosClasseB2);

        classeHeroi.setSalarioBase(salarioBaseClasseB);

        heroi.setPontos(pontosClasseB2);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseB2, heroiService.getSalario(heroi));

    }

    @Test
    @DisplayName("Metodo getSalario() deve trazer o salario correto para classe C")
    void getSalario_heroiValidoRetornarSalarioCorretoParaClasseC(){

        Double salarioBaseClasseC = 10000.0;

        Integer pontosClasseC = 20;

        Double novoSalarioClasseC = salarioBaseClasseC + (100.00 * pontosClasseC);

        classeHeroi.setSalarioBase(salarioBaseClasseC);

        heroi.setPontos(pontosClasseC);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseC, heroiService.getSalario(heroi));

        Integer pontosClasseC2 = 4900;

        Double novoSalarioClasseC2 = salarioBaseClasseC + (100.00 * pontosClasseC2);

        classeHeroi.setSalarioBase(salarioBaseClasseC);

        heroi.setPontos(pontosClasseC2);
        heroi.setClasse(classeHeroi);

        assertEquals(novoSalarioClasseC2, heroiService.getSalario(heroi));

    }

}