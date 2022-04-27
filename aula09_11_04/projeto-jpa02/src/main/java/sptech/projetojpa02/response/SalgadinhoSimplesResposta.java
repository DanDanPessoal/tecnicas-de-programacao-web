package sptech.projetojpa02.response;

public class SalgadinhoSimplesResposta {

    private Integer codigo;
    private String nome;

    public SalgadinhoSimplesResposta(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
