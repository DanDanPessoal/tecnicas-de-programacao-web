package sptech.projetojpa04.controle;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa04.entidade.AnimalEstimacao;
import sptech.projetojpa04.repositorio.AnimalEstimacaoRepository;
import sptech.projetojpa04.resposta.ConsultaAnimalResposta;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class AnimalEstimacaoController {

    @Autowired
    private AnimalEstimacaoRepository repository;

    @PostMapping
    public ResponseEntity postPet(
            @RequestBody @Valid AnimalEstimacao novoPet) {
        repository.save(novoPet);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<AnimalEstimacao>> getPets() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200",
                     content = @Content(mediaType = "text/csv"))
    })
    @GetMapping("/relatorio")
    public ResponseEntity getRelatorio() {
        List<AnimalEstimacao> lista = repository.findAll();
        String relatorio = "";
        for (AnimalEstimacao pet : lista) {
            relatorio += pet.getCodigo()+","+pet.getNome()+"\n";
        }
        return ResponseEntity.status(200)
                    .header("content-type", "text/csv")
                    .header("content-disposition", "filename=\"relatorio-de-pets.csv\"")
                    .body(relatorio);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<AnimalEstimacao> getPet(@PathVariable long codigo) {
        return ResponseEntity.of(repository.findById(codigo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletePet(@PathVariable long codigo) {
        repository.deleteById(codigo);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/filtro/{nome}")
    public ResponseEntity<List<AnimalEstimacao>> filtro(
            @PathVariable String nome
    ) {
        return ResponseEntity.status(200).body(repository.findByNome(nome));
    }

    @GetMapping("/filtro/{nome}/{castrado}")
    public ResponseEntity<List<AnimalEstimacao>> filtro(
            @PathVariable String nome,
            @PathVariable boolean castrado
    ) {
        return ResponseEntity.status(200)
                .body(repository.findByNomeAndCastrado(nome, castrado));
    }

    @GetMapping("/tipo/{codigoTipo}")
    public ResponseEntity<List<AnimalEstimacao>> getAllByTipo(@PathVariable Integer codigoTipo){

        return repository.findByTipoCodigoEquals(codigoTipo).isEmpty()? ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(repository.findByTipoCodigoEquals(codigoTipo));

    }

    @GetMapping("/tipo-nome/cho")
    public ResponseEntity<List<AnimalEstimacao>> getAllByNomeCho(){

        return repository.findByTipoDescricaoContaining("cho").isEmpty() ?
                ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(repository.findByTipoDescricaoContaining("cho"));

    }

    @GetMapping("/filtro-simples/{nome}")
    public ResponseEntity<List<ConsultaAnimalResposta>> getAllByNome(@PathVariable String nome){

        return repository.consultaSimplesPorNome("%"+nome+"%").isEmpty() ?
                ResponseEntity.status(204).build():
                ResponseEntity.status(200).body(repository.consultaSimplesPorNome("%"+nome+"%"));

    }
}
