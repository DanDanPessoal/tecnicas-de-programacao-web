package prova.c201211024danielkatsuakilino.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 4, max = 12, message = "O usuário tem que ter no mínimo 4 caracteres, e 12 caracteres no máximo")
    private String usuario;

    @NotBlank
    @Size(min = 4, max = 8, message = "A senha tem que ter no mínimo 4 caracteres, e 8 caracteres no máximo")
    private String senha;

    @NotBlank
    @Size(min = 10, max = 30, message = "O nome tem que ter no mínimo 10 caracteres, e 30 caracteres no máximo")
    private String nome;

    private boolean autenticar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAutenticar() {
        return autenticar;
    }

    public void setAutenticar(boolean autenticar) {
        this.autenticar = autenticar;
    }
}
