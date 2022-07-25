package sptech.projetocontinuada03.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projetocontinuada03.entidade.ClasseHeroi;

public interface ClasseHeroiRepository extends JpaRepository<ClasseHeroi, Long> {

    // este método está correto e pega a classe de herói a parte de uma pontuação
    ClasseHeroi findTop1ByPontuacaoMinimaLessThanEqualOrderByPontuacaoMinimaDesc(int pontuacao);
}
