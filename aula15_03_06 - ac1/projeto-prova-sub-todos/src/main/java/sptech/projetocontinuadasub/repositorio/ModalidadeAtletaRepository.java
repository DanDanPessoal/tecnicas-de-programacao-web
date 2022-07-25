package sptech.projetocontinuadasub.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.projetocontinuadasub.entidade.Atleta;
import sptech.projetocontinuadasub.entidade.Modalidade;
import sptech.projetocontinuadasub.entidade.ModalidadeAtleta;

import java.util.List;

public interface ModalidadeAtletaRepository extends JpaRepository<ModalidadeAtleta, Long> {

    @Query("select ma.modalidade from ModalidadeAtleta ma where ma.atleta.id = ?1")
    List<Modalidade> getModalidadesPorAtleta(Long idAtleta);

}
