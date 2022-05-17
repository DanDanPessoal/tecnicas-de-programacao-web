package sptech.projetojpa06.resposta;

public class ConsultaAnimalResposta {

    private Long codigo;
    private String nome;
    private String tipo;

    public ConsultaAnimalResposta(Long codigo, String nome, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }
}
