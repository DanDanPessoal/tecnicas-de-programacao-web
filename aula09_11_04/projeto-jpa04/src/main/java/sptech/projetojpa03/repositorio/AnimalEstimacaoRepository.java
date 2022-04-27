package sptech.projetojpa03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetojpa03.entidade.AnimalEstimacao;

import java.util.List;

public interface AnimalEstimacaoRepository
                        extends JpaRepository<AnimalEstimacao, Long> {
    List<AnimalEstimacao> findByNome(String nome);

    List<AnimalEstimacao>findByNomeAndCastrado(String nome, boolean castrado);
}
