package sptech.projetojpa02.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 4700)
public class Salgadinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "port_gen")
    private Integer codigo;

    @NotBlank
    private String nome;

    @NotNull
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
