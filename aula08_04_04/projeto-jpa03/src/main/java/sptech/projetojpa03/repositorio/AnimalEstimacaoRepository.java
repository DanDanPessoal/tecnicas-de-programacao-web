package sptech.projetojpa03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetojpa03.entidade.AnimalEstimacao;

public interface AnimalEstimacaoRepository
                        extends JpaRepository<AnimalEstimacao, Long> {
}
