package sptech.projetojpa01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetojpa01.entity.Filme;

public interface IFilme extends JpaRepository<Filme, Integer> {
    // O Extends JpaRepository serve para mapear a entidade, passando o tipo de sua chave primária.


}
