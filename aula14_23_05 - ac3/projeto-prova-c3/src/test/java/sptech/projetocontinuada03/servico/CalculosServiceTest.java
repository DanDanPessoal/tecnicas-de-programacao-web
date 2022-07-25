//package sptech.projetocontinuada03.servico;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CalculosServiceTest {
//
//    CalculosService service = new CalculosService();
//
//    @Test // isto indica que é um método de teste automatizado
//    @DisplayName("calcularInss() deveria calcular corretamente")
//    void calcularInssCenarioValido() {
//        // ex: 1000 -> 50  / 2000 -> 100
//        // ex: 3000 -> 300 / 5000 -> 500
//
//// verificando um valor da execução do método testado
//// assertEquals(<valor esperado>, <valor do teste>)
//
//        // verifique que é 50 o resultado de service.calcularInss(1000.0)
//        assertEquals(50, service.calcularInss(1000.0));
//        assertEquals(100, service.calcularInss(2000.0));
//        assertEquals(300, service.calcularInss(3000.0));
//        assertEquals(500, service.calcularInss(5000.0));
//    }
//
//    @Test
//    @DisplayName(
//        "calcularInss() deveria lançar exceção p/ salario < 500")
//    void calcularInssDeveLancarErro() {
//        // aqui estamos verificando se ocorreu uma IllegalArgumentException
//        // OBS: não devemos por mais de um teste dentro deste, pois não saberemos qual teste falhou
//        assertThrows(IllegalArgumentException.class, () -> {
//           service.calcularInss(499.99);
//        });
//
//        // testando se houve uma exceção e capturando ela
//        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
//            service.calcularInss(-900.00);
//        });
//
//        // testando a mensagem da exceção
//        assertEquals(
//            "Salário deve ser mais de 500", excecao.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("receberaAuxilio() deve verificar o direito corretamente")
//    void receberaAuxilioCenarioValido() {
//        // este de baixo pode ser simplificado pelos da sequência
//        assertEquals(true, service.receberaAuxilio(600, 0));
//
//        assertTrue(service.receberaAuxilio(600, 0));
//        assertTrue(service.receberaAuxilio(1000, 5));
//        assertFalse(service.receberaAuxilio(3000, 1));
//        assertTrue(service.receberaAuxilio(3000, 4));
//        assertFalse(service.receberaAuxilio(3500, 2));
//    }
//
//    @Test
//    @DisplayName("receberaAuxilio() com salário menor que 500 ou dependentes menores que 0 deve dar erro")
//    void receberaAuxilioLancarErro() {
//        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
//            service.receberaAuxilio(499.99, 1);
//        });
//        assertEquals("O salário deve ser a partir de 500 e os dependentes a partir de 0", excecao.getMessage());
//
//        excecao = assertThrows(IllegalArgumentException.class, () -> {
//            service.receberaAuxilio(800, -2);
//        });
//        assertEquals("O salário deve ser a partir de 500 e os dependentes a partir de 0", excecao.getMessage());
//
//        excecao = assertThrows(IllegalArgumentException.class, () -> {
//            service.receberaAuxilio(499.99, -3);
//        });
//        assertEquals("O salário deve ser a partir de 500 e os dependentes a partir de 0", excecao.getMessage());
//    }
//}