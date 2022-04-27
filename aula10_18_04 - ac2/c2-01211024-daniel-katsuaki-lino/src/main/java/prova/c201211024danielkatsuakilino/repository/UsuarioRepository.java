package prova.c201211024danielkatsuakilino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import prova.c201211024danielkatsuakilino.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    UsuarioEntity findUsuarioEntityByUsuarioAndSenha(String usuario, String senha);

    UsuarioEntity findUsuarioEntityByUsuario(String usuario);

    @Transactional
    @Modifying
    @Query("update UsuarioEntity s set s.autenticar = ?1 where s.id =?2")
    void atualizarAutenticacao(boolean valorAutenticacao, Long idUsuario);

    List<UsuarioEntity> findAllByAutenticarEquals(boolean valor);

}
