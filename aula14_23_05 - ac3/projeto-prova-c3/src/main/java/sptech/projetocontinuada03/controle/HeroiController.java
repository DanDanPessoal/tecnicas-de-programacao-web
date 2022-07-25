package sptech.projetocontinuada03.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import sptech.projetocontinuada03.entidade.ClasseHeroi;
import sptech.projetocontinuada03.entidade.Heroi;
import sptech.projetocontinuada03.entidade.NivelAmeaca;
import sptech.projetocontinuada03.repositorio.ClasseHeroiRepository;
import sptech.projetocontinuada03.repositorio.HeroiRepository;
import sptech.projetocontinuada03.repositorio.NivelAmeacaRepository;
import sptech.projetocontinuada03.request.CadastrarHeroi;
import sptech.projetocontinuada03.servico.HeroiService;

import java.util.List;

@RestController
@RequestMapping("/heroi")
public class HeroiController {

    @Autowired
    private HeroiRepository heroiRepository;

    @Autowired
    private ClasseHeroiRepository classeHeroiRepository;

    @Autowired
    private NivelAmeacaRepository nivelAmeacaRepository;

    @Autowired
    private HeroiService heroiService;

    @PostMapping
    public ResponseEntity<Void> postHeroi(@RequestBody CadastrarHeroi novoHeroi) {

        Heroi heroi = new Heroi();
        heroi.setNome(novoHeroi.getNome());
        heroi.setApelido(novoHeroi.getApelido());
        heroi.setPontos(novoHeroi.getPontos());
        heroi.setClasse(classeHeroiRepository.
                findTop1ByPontuacaoMinimaLessThanEqualOrderByPontuacaoMinimaDesc(novoHeroi.getPontos()));

        heroi.setSalario(heroiService.getSalario(heroi));

        heroiRepository.save(heroi);
        return ResponseEntity.status(201).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putHeroi(@PathVariable Long id, @RequestBody CadastrarHeroi novoHeroi) {

        if(!heroiRepository.existsById(id)){

            return ResponseEntity.status(404).build();

        }

        Heroi heroiAtualizado = heroiRepository.findById(id).get();

        heroiAtualizado.setNome(novoHeroi.getNome());
        heroiAtualizado.setApelido(novoHeroi.getApelido());
        heroiAtualizado.setPontos(novoHeroi.getPontos());
        heroiAtualizado.setClasse(classeHeroiRepository.
                findTop1ByPontuacaoMinimaLessThanEqualOrderByPontuacaoMinimaDesc(novoHeroi.getPontos()));

        heroiAtualizado.setSalario(heroiService.getSalario(heroiAtualizado));

        heroiRepository.save(heroiAtualizado);
        return ResponseEntity.status(200).build();

    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<List<Heroi>> getHerois(String classe) {
        return heroiRepository.findByClasseNomeEquals(classe).isEmpty() ?
                ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(heroiRepository.findByClasseNomeEquals(classe));
    }

    @PatchMapping("/vitoria/{idHeroi}/{idNivelAmeaca}")
    public ResponseEntity patchVitoria(@PathVariable Long idHeroi, @PathVariable Long idNivelAmeaca) {

        if(heroiRepository.existsById(idHeroi) && nivelAmeacaRepository.existsById(idNivelAmeaca)){

            Heroi heroi = heroiRepository.findById(idHeroi).get();
            NivelAmeaca nivelAmeaca = nivelAmeacaRepository.findById(idNivelAmeaca).get();
            Integer pontuacao = heroi.getPontos() + nivelAmeaca.getPontuacao();

            ClasseHeroi classeHeroi = classeHeroiRepository.
                    findTop1ByPontuacaoMinimaLessThanEqualOrderByPontuacaoMinimaDesc(pontuacao);

            heroi.setClasse(classeHeroi);
            heroi.setPontos(pontuacao);

            heroi.setSalario(heroiService.getSalario(heroi));

            heroiRepository.save(heroi);

            heroiRepository.atualizarHeroi(idHeroi, classeHeroi);

            return ResponseEntity.status(200).build();

        }

        return ResponseEntity.status(404).build();

    }

    @GetMapping
    public ResponseEntity<List<Heroi>> getAllHerois(){

        return heroiRepository.findAll().isEmpty() ? ResponseEntity.status(204).build():
                ResponseEntity.status(200).body(heroiRepository.findAll());

    }
}
