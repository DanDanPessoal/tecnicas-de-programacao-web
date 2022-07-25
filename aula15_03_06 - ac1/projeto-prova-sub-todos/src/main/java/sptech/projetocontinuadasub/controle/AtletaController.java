package sptech.projetocontinuadasub.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projetocontinuadasub.entidade.Atleta;
import sptech.projetocontinuadasub.entidade.Modalidade;
import sptech.projetocontinuadasub.entidade.ModalidadeAtleta;
import sptech.projetocontinuadasub.repositorio.AtletaRepository;
import sptech.projetocontinuadasub.repositorio.ModalidadeAtletaRepository;
import sptech.projetocontinuadasub.repositorio.ModalidadeRepository;
import sptech.projetocontinuadasub.request.cadastrarAtleta;
import sptech.projetocontinuadasub.servico.AtletaService;

import java.util.List;

@RestController
@RequestMapping("/atletas")
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepository;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Autowired
    private ModalidadeAtletaRepository modalidadeAtletaRepository;

    @Autowired
    private AtletaService atletaService;

    @GetMapping
    public ResponseEntity<List<Atleta>> getAtletas(){

        List<Atleta> lista = atletaRepository.findAll();

        return lista.isEmpty()? ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(lista);

    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody cadastrarAtleta novoAtleta) {

        Atleta atleta = new Atleta(novoAtleta.getNome(), novoAtleta.getApelido(), 0.0);

        atletaRepository.save(atleta);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/modalidade/{idAtleta}/{idModalidade}")
    public ResponseEntity incluirModalidade(@PathVariable Long idAtleta, @PathVariable Long idModalidade) {
        List<Modalidade> modalidades = modalidadeAtletaRepository.getModalidadesPorAtleta(idAtleta);

        if(!atletaRepository.existsById(idAtleta) || !modalidadeRepository.existsById(idModalidade)){

            return ResponseEntity.status(404).build();

        }

        Modalidade modalidade = modalidadeRepository.findById(idModalidade).get();
        Atleta atleta = atletaRepository.findById(idAtleta).get();

        modalidadeAtletaRepository.save(new ModalidadeAtleta(modalidade, atleta));
        modalidades.add(modalidade);
        atletaService.setSalario(atleta, modalidades);
        atletaRepository.atualizarSalario(idAtleta, atleta.getSalario());

        return ResponseEntity.status(200).build();

    }

    @GetMapping("/{id}/modalidades")
    public ResponseEntity<List<Modalidade>> getModalidadesAtleta(@PathVariable Long id) {

        if(atletaRepository.existsById(id)){

            List<Modalidade> modalidades = modalidadeAtletaRepository.getModalidadesPorAtleta(id);

            return modalidades.isEmpty()? ResponseEntity.status(204).build():
                    ResponseEntity.status(200).body(modalidades);

        }

        return ResponseEntity.status(404).build();
    }

}
