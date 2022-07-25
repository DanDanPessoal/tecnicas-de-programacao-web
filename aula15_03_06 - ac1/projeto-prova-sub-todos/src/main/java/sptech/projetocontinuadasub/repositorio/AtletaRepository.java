package sptech.projetocontinuadasub.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.projetocontinuadasub.entidade.Atleta;

import javax.transaction.Transactional;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {

    @Transactional
    @Modifying
    @Query("update Atleta a set a.salario = ?2 where a.id = ?1")
    void atualizarSalario(Long id, Double salario);

}
