package sptech.projetojpa07.servico;

public class CalculoService {

    public Double calcularInss(Double salarioBruto){

        if( salarioBruto < 500) {throw new IllegalArgumentException("Salário deve ter mais que 500");}

        return salarioBruto <= 2000 ? salarioBruto * 0.05 : salarioBruto * 0.1;

    }

    public boolean receberaAuxilio(Double salario, int qtdDependentes){

        if(salario < 500 || qtdDependentes < 0){

            throw new IllegalArgumentException("O salário deve ser a partir de 500 e os" +
                    " dependentes a partir de 0");

        }else if(salario < 2000 || (salario < 4000 && qtdDependentes >= 3)){

            return true;

        }

        return false;

    }
}
