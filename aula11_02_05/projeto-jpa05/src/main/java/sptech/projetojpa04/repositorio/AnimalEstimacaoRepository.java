package sptech.projetojpa04.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.projetojpa04.entidade.AnimalEstimacao;
import sptech.projetojpa04.resposta.ConsultaAnimalResposta;

import java.util.List;

public interface AnimalEstimacaoRepository
                        extends JpaRepository<AnimalEstimacao, Long> {

    /*
        Aqui usamos um DYNAMIC FINDER
        findBy -> indica uma consulta
        Nome -> Qual campo estará no "where" da consulta
        Mais detalhes e exemplos na Apostila que está no Moodle
     */
    List<AnimalEstimacao> findByNome(String nome);

    List<AnimalEstimacao> findByNomeAndCastrado(String str, boolean bool);

    @Query("select a from AnimalEstimacao a where a.nome = ?1")
    List<AnimalEstimacao> pesquisaPorNome(String nome);

    @Query("select a from AnimalEstimacao a where a.nome = ?1 and a.castrado = ?2")
    List<AnimalEstimacao> pesquisaPorNomeCastrado(
                                    String nome, boolean castrado);

    List<AnimalEstimacao> findByTipoCodigoEquals(int codigo);

    List<AnimalEstimacao> findByTipoDescricaoContaining(String comeco);

    List<AnimalEstimacao> findByTipoDescricaoContainingIgnoreCase(String comeco);

    @Query("select new sptech.projetojpa04.resposta.ConsultaAnimalResposta(a.codigo, a.nome," +
            " a.tipo.descricao) from AnimalEstimacao a where a.nome like ?1")
    List<ConsultaAnimalResposta> consultaSimplesPorNome(String nomeAnimal);

}
