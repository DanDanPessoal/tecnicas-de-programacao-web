package prova.c201211024danielkatsuakilino.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutenticarUsuarioRequest {

    @NotBlank
    @Size(min = 4, max = 12, message = "O usuário tem que ter no mínimo 4 caracteres, e 12 caracteres no máximo")
    private String usuario;

    @NotBlank
    @Size(min = 4, max = 8, message = "A senha tem que ter no mínimo 4 caracteres, e 8 caracteres no máximo")
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
