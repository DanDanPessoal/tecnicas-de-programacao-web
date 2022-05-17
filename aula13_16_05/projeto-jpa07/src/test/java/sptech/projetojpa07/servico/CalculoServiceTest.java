package sptech.projetojpa07.servico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoServiceTest {

    CalculoService c = new CalculoService();

    @Test
    @DisplayName("CalcularInss() deve calcular corretamente)")
    void calcularInss_deveriaCalcularCorretamente() {

        assertEquals(25, c.calcularInss(500.0));

        assertEquals(100, c.calcularInss(2000.0));

        assertEquals(500, c.calcularInss(5000.0));

    }

    @Test
    @DisplayName("calcularInss() com salário menor que 500 deve dar erro")
    void calcularInss_salarioInvalidoErro(){

        assertThrows(IllegalArgumentException.class, () -> {
            CalculoService c = new CalculoService();
            c.calcularInss(-100.0);
        });

        IllegalArgumentException execao = assertThrows(IllegalArgumentException.class, () -> {
            CalculoService c = new CalculoService();
            c.calcularInss(499.99);
        });

        assertEquals("Salário deve ter mais que 500", execao.getMessage());

    }

    @Test
    @DisplayName("a receberaAuxilio() deve devolver true")
    void receberaAuxilio_deveDevolverTrue(){

        assertTrue(c.receberaAuxilio(1999.0, 0));

        assertTrue(c.receberaAuxilio(500.0, 0));

        assertTrue(c.receberaAuxilio(3999.99, 9));

        assertTrue(c.receberaAuxilio(3000.00, 4));

    }

    @Test
    @DisplayName("A receberaAuxilio() deve devolver false")
    void receberaAuxilio_deveDevolverFalse(){

        assertFalse(c.receberaAuxilio(5000.0, 9));

        assertFalse(c.receberaAuxilio(2000.0, 2));

    }

    @Test
    @DisplayName("A receberaAuxilio() deve lançar uma excecao")
    void receberaAuxilio_deveDevolverExececao(){

        assertThrows( IllegalArgumentException.class,() -> { c.receberaAuxilio(300.0,2);
        });

        assertThrows( IllegalArgumentException.class,() -> { c.receberaAuxilio(1999.99,-1);
        });

    }
}