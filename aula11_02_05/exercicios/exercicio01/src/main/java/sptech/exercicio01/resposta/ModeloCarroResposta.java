package sptech.exercicio01.resposta;

public class ModeloCarroResposta {

    private Integer idCarro;
    private String modelo;
    private String fabricante;
    private String pais;

    public ModeloCarroResposta(Integer idCarro, String modelo, String fabricante, String pais) {
        this.idCarro = idCarro;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.pais = pais;
    }

    public Integer getIdCarro() {
        return idCarro;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getPais() {
        return pais;
    }
}
