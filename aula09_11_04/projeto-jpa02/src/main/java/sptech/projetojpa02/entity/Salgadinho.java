package sptech.projetojpa02.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Salgadinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotBlank
    private String nome;

    @NotNull
    @Range(min = 0, max = 10)
    private Integer nivelSal;

    @Positive
    private Double preco;

    @NotNull
    private Boolean apimentado;

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

    public Integer getNivelSal() {
        return nivelSal;
    }

    public void setNivelSal(Integer nivelSal) {
        this.nivelSal = nivelSal;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getApimentado() {
        return apimentado;
    }

    public void setApimentado(Boolean apimentado) {
        this.apimentado = apimentado;
    }
}
