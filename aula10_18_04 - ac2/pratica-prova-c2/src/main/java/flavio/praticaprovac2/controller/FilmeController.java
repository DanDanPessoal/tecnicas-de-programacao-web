package flavio.praticaprovac2.controller;

import flavio.praticaprovac2.entity.Filme;
import flavio.praticaprovac2.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @PostMapping
    public ResponseEntity postFilme(@RequestBody Filme novoFilme) {
        repository.save(novoFilme);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getFilme(@PathVariable Long id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFilme(@PathVariable Long id) {
        repository.deleteById((id));
        return ResponseEntity.status(200).build();
    }

    @GetMapping
    public ResponseEntity<List<Filme>> getFilmes() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PatchMapping("/{id}/avaliacao/{avaliacao}")
    public ResponseEntity patchFilme(@PathVariable Long id, @PathVariable Integer avaliacao) {
        if (repository.existsById(id)) {
            repository.updateFilme(id, avaliacao);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/contagem-cadastrados")
    public ResponseEntity getContagem() {
        return ResponseEntity.status(200).body(repository.count());
    }

    @GetMapping("/relatorio")
    public ResponseEntity getRelatorio() {
        List<Filme> lista = repository.findAll();
        String relatorio = "";
        for (Filme filme : lista){
            relatorio += filme.getId()+","+filme.getNome()+","+filme.getAvaliacao()+"\n";
        }
        return ResponseEntity.status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"relatorioFilmes.csv\"")
                .body(relatorio);
    }

}
