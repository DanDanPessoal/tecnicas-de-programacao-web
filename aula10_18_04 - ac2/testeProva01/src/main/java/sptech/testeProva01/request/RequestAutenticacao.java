package sptech.testeProva01.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestAutenticacao {
    @NotBlank
    @NotNull
    @Size(min = 4, max = 12,message = "O login precisa ter de 4 até 12 caracteres")
    private String login;

    @NotBlank
    @NotNull
    @Size(min = 4, max = 8, message = "A senha precisa ter de 4 até 8 caracteres")
    private String senha;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String recuperaSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
