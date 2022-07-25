package sptech.projetocontinuadasub.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetocontinuadasub.entidade.Atleta;
import sptech.projetocontinuadasub.entidade.Modalidade;

public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {
}
