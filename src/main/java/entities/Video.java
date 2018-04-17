package entities;

public class Video {

    Integer idVideo;
    String nomVideo;

    public Video(Integer idVideo, String nomVideo) {
        this.idVideo = idVideo;
        this.nomVideo = nomVideo;
    }

    public Integer getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Integer idVideo) {
        this.idVideo = idVideo;
    }

    public String getNomVideo() {
        return nomVideo;
    }

    public void setNomVideo(String nomVideo) {
        this.nomVideo = nomVideo;
    }
}
