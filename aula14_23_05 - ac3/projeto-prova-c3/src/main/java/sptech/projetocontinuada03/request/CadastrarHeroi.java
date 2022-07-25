package sptech.projetocontinuada03.request;

public class CadastrarHeroi {

    private String nome;

    private String apelido;

    private Integer pontos;

    public CadastrarHeroi( String nome, String apelido, Integer pontos) {
        this.nome = nome;
        this.apelido = apelido;
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

}
