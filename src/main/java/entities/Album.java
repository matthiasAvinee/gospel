package entities;

public class Album {

    Integer idAlbum;
    String nomAlbum;


    public Album(Integer idAlbum, String nomAlbum) {
        this.idAlbum = idAlbum;
        this.nomAlbum = nomAlbum;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNomAlbum() {
        return nomAlbum;
    }

    public void setNomAlbum(String nomAlbum) {
        this.nomAlbum = nomAlbum;
    }
}
