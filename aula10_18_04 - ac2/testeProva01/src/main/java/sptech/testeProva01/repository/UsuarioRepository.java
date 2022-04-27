package sptech.testeProva01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.testeProva01.entidade.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    /* @Query("update usuario u set u.autenticado = ?2 where u.id = ?1")
    public void atualizaAutenticado ( Long id, boolean autenticado);
*/
}

