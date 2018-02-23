package entities;

public class Photo {
    Integer idPhoto;
    Album album;

    public Photo(Integer idPhoto, Album album) {
        this.idPhoto = idPhoto;
        this.album = album;
    }

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
