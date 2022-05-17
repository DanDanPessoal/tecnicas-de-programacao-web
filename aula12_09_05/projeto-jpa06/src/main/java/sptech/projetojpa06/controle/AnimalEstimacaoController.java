package sptech.projetojpa06.controle;

import feign.Feign;
import feign.FeignException;
import feign.Response;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetojpa06.clientcep.resposta.Cep;
import sptech.projetojpa06.clientlog.resposta.Log;
import sptech.projetojpa06.clientlog.service.ApiLogService;
import sptech.projetojpa06.clientraca.resposta.Raca;
import sptech.projetojpa06.clientraca.service.ApiRacaService;
import sptech.projetojpa06.clientcep.service.ViaCepService;
import sptech.projetojpa06.entidade.AnimalEstimacao;
import sptech.projetojpa06.repositorio.AnimalEstimacaoRepository;
import sptech.projetojpa06.resposta.ConsultaAnimalResposta;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/pets")
public class AnimalEstimacaoController {

    @Autowired
    private AnimalEstimacaoRepository repository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private ApiRacaService apiRacaService;

    @Autowired
    private ApiLogService apiLogService;

    @PostMapping
    public ResponseEntity postPet(
            @RequestBody @Valid AnimalEstimacao novoPet) {
        repository.save(novoPet);
        return status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<AnimalEstimacao>> getPets() {
        return status(200).body(repository.findAll());
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
        return status(200)
                    .header("content-type", "text/csv")
                    .header("content-disposition", "filename=\"relatorio-de-pets.csv\"")
                    .body(relatorio);
    }

    @PatchMapping("/endereco/{id}/{cep}/{complemento}")
    public ResponseEntity patchEndereco(@PathVariable Long id,
                                        @PathVariable String cep, @PathVariable String complemento){

        try {

            Cep cepEncontrado = viaCepService.getCep(cep);


            AnimalEstimacao pet = repository.getById(id);

            pet.setEndereco(cepEncontrado.getLogradouro() + " " + complemento);
            pet.setBairro(cepEncontrado.getBairro());
            pet.setCidade(cepEncontrado.getLocalidade());
            pet.setUf(cepEncontrado.getUf());
            pet.setCep(cep);

            repository.save(pet);

            Log log = new Log("Novo endereco D", id);
            apiLogService.postLog(log);

            return ResponseEntity.status(200).build();

        }catch (FeignException e){

            switch (e.status()){

                case 404:
                    return ResponseEntity.status(404).body("Cep Não Encontrado");

                case 400:
                    return ResponseEntity.status(400).body("Cep Inválido");

                case 500:
                    return ResponseEntity.status(500).body("Serviço de cep indisponível");

                case 0:
                    return ResponseEntity.status(500).body("Serviço de Cep Inacessivel");

                default:
                    return ResponseEntity.status(500).body("Erro no Serviço de cep");
            }

        }

    }

    @PatchMapping("/raca/{idPet}/{idRaca}")
    public ResponseEntity atualizarRacaPet(@PathVariable Long idPet, @PathVariable String idRaca){

        if(!repository.existsById(idPet)){

            return ResponseEntity.status(404).body("Pet não encontrado");

        }

        try {

            Raca raca = apiRacaService.getRaca(idRaca);

            AnimalEstimacao animal = repository.getById(idPet);

            animal.setRaca(raca.getName());

            repository.save(animal);

            Log log = new Log("Novo Pet D", idPet);
            apiLogService.postLog(log);

        }catch (FeignException e){

            if(e.status() == 404){

                return ResponseEntity.status(400).body("Raça não encontrada");

            }

        }

            return ResponseEntity.status(200).body("Pet Atualizado");
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<AnimalEstimacao> getPet(@PathVariable long codigo) {
        return of(repository.findById(codigo));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletePet(@PathVariable long codigo) {
        repository.deleteById(codigo);
        return status(200).build();
    }

    @GetMapping("/filtro/{nome}")
    public ResponseEntity<List<AnimalEstimacao>> filtro(
            @PathVariable String nome
    ) {
        return status(200).body(repository.findByNome(nome));
    }

    @GetMapping("/filtro/{nome}/{castrado}")
    public ResponseEntity<List<AnimalEstimacao>> filtro(
            @PathVariable String nome,
            @PathVariable boolean castrado
    ) {
        return status(200)
                .body(repository.findByNomeAndCastrado(nome, castrado));
    }

    /*
    GET /pets/tipo/{codigoTipo}
    Ex: /pets/tipo/1 -> traria todos os pets que são "gato"
     */
    @GetMapping("/tipo/{codigoTipo}")
    public ResponseEntity getPorTipo(@PathVariable int codigoTipo) {
        List<AnimalEstimacao> lista = repository.findByTipoCodigo(codigoTipo);
        if (lista.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(lista);
        }
    }

    /*
    GET /pets/tipo-nome/{nomeTipo}
    Ex: /pets/tipo-nome/cho -> traria todos os pets contém "cho" na descricao do tipo
     */
    @GetMapping("/tipo-nome/{nomeTipo}")
    public ResponseEntity getPorDescricao(@PathVariable String nomeTipo) {
        List<AnimalEstimacao> lista = repository.findByTipoDescricaoContains(nomeTipo);
        if (lista.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(lista);
        }
    }

    @GetMapping("/filtro-simples/{nome}")
    public ResponseEntity getSimplesPorNome(@PathVariable String nome) {
        List<ConsultaAnimalResposta> lista =
                repository.consultaSimplePorNome("%"+nome+"%");
        if (lista.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(lista);
        }
    }
}
