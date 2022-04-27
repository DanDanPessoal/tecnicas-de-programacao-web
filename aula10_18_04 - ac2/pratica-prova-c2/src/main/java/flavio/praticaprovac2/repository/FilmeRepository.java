package flavio.praticaprovac2.repository;

import flavio.praticaprovac2.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Transactional
    @Modifying
    @Query("update Filme f set f.avaliacao =?2 where f.id=?1")
    void updateFilme (Long id, Integer avaliacao);

}
