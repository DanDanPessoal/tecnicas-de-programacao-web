package sptech.projetojpa03.entidade;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity // do pacote javax.persistence
public class AnimalEstimacao {

    @Id // do pacote javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "F")
    @Size(min = 2, max = 20, message = "Burrao")
    private String nome;

    @PastOrPresent
    private LocalDate dataNascimento;

    @Positive
    @Max(50)
    @NotNull
    private Double peso;

    private boolean castrado;

    @Email
    private String email_dono;

    @CPF
    @Size(max = 11, message = "Envie apenas numeros")
    private String cpf_dono;

    @Pattern(regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
            message = "Informe um telefone válido com ou sem DDD")
    private String telefone_dono;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getEmailDono() {
        return email_dono;
    }

    public void setEmailDono(String email) {
        this.email_dono = email;
    }

    public String getCpfDono() {
        return cpf_dono;
    }

    public void setCpfDono(String cpf) {
        this.cpf_dono = cpf;
    }

    public String getTelefoneDono() {
        return telefone_dono;
    }

    public void setTelefoneDono(String telefoneDono) {
        this.telefone_dono = telefoneDono;
    }
}
