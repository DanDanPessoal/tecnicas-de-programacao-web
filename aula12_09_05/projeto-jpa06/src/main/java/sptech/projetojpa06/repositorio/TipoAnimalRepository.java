package sptech.projetojpa06.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetojpa06.entidade.TipoAnimal;

public interface TipoAnimalRepository extends JpaRepository<TipoAnimal, Integer> {
}
