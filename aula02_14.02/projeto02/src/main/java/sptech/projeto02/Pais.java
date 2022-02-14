package sptech.projeto02;

public class Pais {

    private String nome;
    private int medalhasOuro;
    private int medalhasPrata;
    private int MedalhasBronze;

    public Pais(String nome, int medalhasOuro, int medalhasPrata, int MedalhasBronze) {
        this.nome = nome;
        this.medalhasOuro = medalhasOuro;
        this.medalhasPrata = medalhasPrata;
        this.MedalhasBronze = MedalhasBronze;
    }

    public Pais() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMedalhasOuro() {
        return medalhasOuro;
    }

    public void setMedalhasOuro(int medalhasOuro) {
        this.medalhasOuro = medalhasOuro;
    }

    public int getMedalhasPrata() {
        return medalhasPrata;
    }

    public void setMedalhasPrata(int medalhasPrata) {
        this.medalhasPrata = medalhasPrata;
    }

    public int getMedalhasBronze() {
        return MedalhasBronze;
    }

    public void setMedalhasBronze(int getMedalhasBronze) {
        this.MedalhasBronze = getMedalhasBronze;
    }

    public int getTotalMedalhas(){

        return (this.getMedalhasOuro()+ this.getMedalhasPrata() + this.getMedalhasBronze());

    }
}
