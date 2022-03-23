package sptech.projetojpa01.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // - Anotação do javax.persistence.Entity
        // Serve para o JPA criar uma tabela apartir dessa classe
public class Filme {

    @Id // - Anotação do javax.persistence.Entity
        // Serve para definir o atributo que será a chavePrimaria do banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        // essa anotação equivale o auto_increment
    private Integer codigo;

    private String nome;
    private Integer anoLancamento;
    private boolean classico;
    private Double avaliacao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public boolean isClassico() {
        return classico;
    }

    public void setClassico(boolean classico) {
        this.classico = classico;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
