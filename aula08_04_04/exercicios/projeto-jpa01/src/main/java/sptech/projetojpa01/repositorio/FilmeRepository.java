package sptech.projetojpa01.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetojpa01.entidade.Filme;

public interface FilmeRepository
                    extends JpaRepository<Filme, Integer> {
}
