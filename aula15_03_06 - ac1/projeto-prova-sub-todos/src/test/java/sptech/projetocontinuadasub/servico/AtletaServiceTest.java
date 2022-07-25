package sptech.projetocontinuadasub.servico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sptech.projetocontinuadasub.entidade.Atleta;
import sptech.projetocontinuadasub.entidade.Modalidade;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtletaServiceTest {

    Atleta atleta;
    List<Modalidade> modalidadeList;
    AtletaService atletaService = new AtletaService();

    @Test
    @DisplayName("Metodo setSalario() deve dar um exception se atleta for nulo")
    void setSalario_atletaNuloRetornarIllegalArgumentException() {

        atleta = null;
        modalidadeList = new ArrayList<>();
        modalidadeList.add(new Modalidade());

        assertThrows(IllegalArgumentException.class, () ->{

            atletaService.setSalario(atleta,modalidadeList);

        });
    }

    @Test
    @DisplayName("Metodo setSalario() deve dar um exception se uma modalidade da lista for nula")
    void setSalario_modalidadeNulaRetornarIllegalArgumentException() {

        atleta = new Atleta();
        modalidadeList = new ArrayList<>();
        modalidadeList.add(null);

        assertThrows(IllegalArgumentException.class, () ->{

            atletaService.setSalario(atleta,modalidadeList);

        });
    }

    @Test
    @DisplayName("Metodo setSalario() deve dar um exception se lista de modalidades for vazia")
    void setSalario_modalidadeListVaziaRetornarIllegalArgumentException() {

        atleta = new Atleta();
        modalidadeList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () ->{

            atletaService.setSalario(atleta,modalidadeList);

        });
    }

    @Test
    @DisplayName("Metodo setSalario() nao deve aumentar o salario do atleta")
    void setSalario_naoAumentarSalarioAtleta() {

        atleta = new Atleta("teste", "tes", 7000.0);
        modalidadeList = new ArrayList<>();
        modalidadeList.add(new Modalidade(5000.0, 20000.0));

        atletaService.setSalario(atleta, modalidadeList);

        assertEquals(7000.0, atleta.getSalario());

        atleta = new Atleta("teste", "tes", 19999.9);
        atletaService.setSalario(atleta, modalidadeList);

        assertEquals(19999.9, atleta.getSalario());
    }

    @Test
    @DisplayName("Metodo setSalario() deve aumentar o salario do atleta")
    void setSalario_AumentarSalarioAtleta() {

        atleta = new Atleta("teste", "tes", 3000.00);
        modalidadeList = new ArrayList<>();
        modalidadeList.add(new Modalidade(5000.0, 20000.0));
        modalidadeList.add(new Modalidade(8000.0, 80000.0));

        atletaService.setSalario(atleta, modalidadeList);

        assertEquals(8000.0, atleta.getSalario());

    }
}