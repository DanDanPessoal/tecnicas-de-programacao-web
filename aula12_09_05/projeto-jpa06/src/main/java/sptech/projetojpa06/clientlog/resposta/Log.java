package sptech.projetojpa06.clientlog.resposta;

import java.time.LocalDateTime;

public class Log {

    private String descricao;
    private Long identificador;

    public Log(String descricao, Long identificador) {
        this.descricao = descricao;
        this.identificador = identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }
}
