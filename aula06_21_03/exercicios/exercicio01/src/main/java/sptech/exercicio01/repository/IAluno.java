package sptech.exercicio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.exercicio01.entity.Aluno;

public interface IAluno extends JpaRepository<Aluno, Integer> {

}
