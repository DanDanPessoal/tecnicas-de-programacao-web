package sptech.projetocontinuadasub.entidade;

import javax.persistence.*;

@Entity
public class ModalidadeAtleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Modalidade modalidade;

    @ManyToOne
    private Atleta atleta;

    public ModalidadeAtleta(Modalidade modalidade, Atleta atleta) {
        this.modalidade = modalidade;
        this.atleta = atleta;
    }

    public ModalidadeAtleta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }
}
