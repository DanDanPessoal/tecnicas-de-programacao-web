package sptech.exercicio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sptech.exercicio01.entity.Carro;
import sptech.exercicio01.resposta.ModeloCarroResposta;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    List<Carro> findByFabricanteCodigoEquals(Integer codigo);

    Integer countByFabricanteCodigoEquals(Integer codigo);

    Integer countByFabricantePaisNomeEquals(String nomePais);

    @Query("select new sptech.exercicio01.resposta.ModeloCarroResposta(c.codigo, c.modelo," +
            " c.fabricante.nome, c.fabricante.pais.nome) from Carro c where c.modelo like ?1")
    List<ModeloCarroResposta> getModeloCarro(String modeloCarro);

}
