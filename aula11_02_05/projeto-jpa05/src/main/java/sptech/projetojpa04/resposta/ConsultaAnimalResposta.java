package sptech.projetojpa04.resposta;

public class ConsultaAnimalResposta {

    private Long codigoAnimal;
    private String nomeAnimal;
    private String tipo;

    public ConsultaAnimalResposta(Long codigoAnimal, String nomeAnimal, String tipo) {
        this.codigoAnimal = codigoAnimal;
        this.nomeAnimal = nomeAnimal;
        this.tipo = tipo;
    }

    public Long getCodigoAnimal() {
        return codigoAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public String getTipo() {
        return tipo;
    }
}
