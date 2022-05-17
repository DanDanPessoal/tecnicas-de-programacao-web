package sptech.projetojpa04.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.projetojpa04.entidade.TipoAnimal;

@Repository
public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Integer> {
}
