package entities;

public class Musique {

    Integer idMusique;
    String nomMusique;

    public Musique(Integer idMusique, String nomMusique) {
        this.idMusique = idMusique;
        this.nomMusique = nomMusique;
    }

    public Integer getIdMusique() {
        return idMusique;
    }

    public void setIdMusique(Integer idMusique) {
        this.idMusique = idMusique;
    }

    public String getNomMusique() {
        return nomMusique;
    }

    public void setNomMusique(String nomMusique) {
        this.nomMusique = nomMusique;
    }
}
