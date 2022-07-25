package sptech.projetocontinuada03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.projetocontinuada03.entidade.ClasseHeroi;
import sptech.projetocontinuada03.entidade.Heroi;

import javax.transaction.Transactional;
import java.util.List;

public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    List<Heroi> findByClasseNomeEquals(String classe);

    @Transactional
    @Modifying
    @Query("update Heroi h set h.classe = ?2 where h.id = ?1")
    void atualizarHeroi(Long id, ClasseHeroi classeHeroi);

}
