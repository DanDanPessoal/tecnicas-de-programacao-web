package sptech.testeProva01.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
    @Id // do pacote javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 4, max = 12,message = "O login precisa ter de 4 até 12 caracteres")
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 10, max = 30, message = "O nome precisa ter de 10 até 30 caracteres")
    private String nome;

    @NotBlank
    @NotNull
    @Size(min = 4, max = 8, message = "A senha precisa ter de 4 até 8 caracteres")
    private String senha;

    private boolean autenticado = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String recuperaSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public boolean autenticar(String usuario, String senha) {
        autenticado = usuario.equals(this.login) && senha.equals(this.senha);
        return autenticado;
    }


}
