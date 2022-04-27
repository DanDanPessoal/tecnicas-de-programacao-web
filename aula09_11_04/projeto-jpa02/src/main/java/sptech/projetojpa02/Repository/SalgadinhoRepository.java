package sptech.projetojpa02.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sptech.projetojpa02.entity.Salgadinho;
import sptech.projetojpa02.response.SalgadinhoSimplesResposta;

import java.util.List;

public interface SalgadinhoRepository extends JpaRepository<Salgadinho, Integer> {

    @Query("select new sptech.projetojpa02.response.SalgadinhoSimplesResposta(s.codigo, s.nome) from Salgadinho s")
    List<SalgadinhoSimplesResposta> listaSimples();

    // Quando precisamos modificar valores no banco, tem que se utilizar as anotações
    //  @Transactional (pacote springframework)
    //  @Modifying (pacote springframework)
    @Transactional
    @Modifying
    @Query("update Salgadinho s set s.preco = ?2 where s.codigo =?1")
    void atualizarPreco(int codigo, Double preco);

    @Transactional
    @Modifying
    @Query("update Salgadinho s set s.preco = ?2 , s.apimentado = ?3 where s.codigo = ?1")
    void atualizarPrecoApimentado(Integer codigo, Double preco, Boolean apimentado);

    List<Salgadinho> findAllByApimentadoTrue();

    Integer countSalgadinhoByApimentadoFalse();

    List<Salgadinho> findAllByNivelSalIsLessThan(Integer value);

    List<Salgadinho> findAllByPrecoGreaterThanEqual(Double value);

}

