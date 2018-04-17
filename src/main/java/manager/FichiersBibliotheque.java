package manager;

import dao.impl.impl.FichiersDaoImpl;
import entities.*;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


/**
 * Explication des fonctions sur FichiertDaoImpl
 */

public class FichiersBibliotheque {

    private static final String IMAGE_DIRECTORY_PATH = "C:/projet100h/images";
    private static final String PDF_DIRECTORY_PATH = "C:/projet100h/pdf";
    private static final String VIDEO_DIRECTORY_PATH = "C:/projet100h/videos";
    private static final String MUSIC_DIRECTORY_PATH = "C:/projet100h/enregistrements";

    private static class FichiersBibliothequeHolder {
        private final static FichiersBibliotheque instance = new FichiersBibliotheque();
    }

    public static FichiersBibliotheque getInstance() {
        return FichiersBibliothequeHolder.instance;
    }

    private FichiersDaoImpl fichiersDao = new FichiersDaoImpl();

    public List<Album> listAlbums() { return fichiersDao.listAlbums(); }

    public Album getAlbum(Integer id) {
        return fichiersDao.getAlbum(id);
    }

    public void addAlbum(String nomAlbum){fichiersDao.addAlbum(nomAlbum);}

    public void supprimerAlbum(Integer id){ fichiersDao.supprimerAlbum(id);}

    public Path getPhotoPicturePath(Integer id){
        Path picturePath = fichiersDao.getPicturePath(id);

        return picturePath;
    }

    public List<Photo> listPhotos(Integer id){return fichiersDao.listPhotos(id);}

    public Photo getPhoto(Integer id) {
        return fichiersDao.getPhoto(id);
    }

    public Photo addPhoto(Photo photo, Part picture) {

        // on génère un nombre aléatoire afin de ne pas avoir de doublons dans la base de données
        String filename = UUID.randomUUID().toString().substring(0,8) + "-" + picture.getSubmittedFileName();
        Path picturePath = null;

        if (picture!=null) {
            picturePath = Paths.get(IMAGE_DIRECTORY_PATH, filename);
            try {
                Files.copy(picture.getInputStream(), picturePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fichiersDao.addPhoto(photo, picturePath);
    }

    public void supprimerPhoto(Integer id){fichiersDao.supprimerPhoto(id);}

    public List<Path> listPathPDF(){return  fichiersDao.listPathPDF();}

    public Path getPDFPath(Integer id){
        Path PDFPath = fichiersDao.getPDFPath(id);

        return PDFPath;
    }

    public List<PDF> listPDF(){return fichiersDao.listPDF();}

    public PDF getPDF(Integer id) {
        return fichiersDao.getPDF(id);
    }

    public PDF addPDF(PDF pdf, Part filePDF) {

        // on génère un nombre aléatoire afin de ne pas avoir de doublons dans la base de données
        String filename = UUID.randomUUID().toString().substring(0,8) + "-" + filePDF.getSubmittedFileName();
        Path PDFPath = null;

        if (filePDF!=null) {
            PDFPath = Paths.get(PDF_DIRECTORY_PATH, filename);
            try {
                Files.copy(filePDF.getInputStream(), PDFPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fichiersDao.addPDF(pdf, PDFPath);
    }

    public void supprimerPDF(Integer id){fichiersDao.supprimerPDF(id);}

    /**
     *
     * Vidéos
     *
     */

    public List<Path> listPathVideos(){return  fichiersDao.listPathVideos();}

    public Path getVideoPath(Integer id){
        Path VideoPath = fichiersDao.getVideoPath(id);

        return VideoPath;
    }

    public List<Video> listVideos(){return fichiersDao.listVideos();}

    public Video getVideo(Integer id) {
        return fichiersDao.getVideo(id);
    }

    public Video addVideo(Video video, Part fileVideo) {

        // on génère un nombre aléatoire afin de ne pas avoir de doublons dans la base de données
        String filename = UUID.randomUUID().toString().substring(0,8) + "-" + fileVideo.getSubmittedFileName();
        Path VideoPath = null;

        if (fileVideo!=null) {
            VideoPath = Paths.get(VIDEO_DIRECTORY_PATH, filename);
            try {
                Files.copy(fileVideo.getInputStream(), VideoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fichiersDao.addVideo(video, VideoPath);
    }

    public void supprimerVideo(Integer id){fichiersDao.supprimerVideo(id);}

    /**
     *
     * Musiques
     *
     */

    public List<Path> listPathMusiques(){return  fichiersDao.listPathMusiques();}

    public Path getMusiquePath(Integer id){
        Path VideoPath = fichiersDao.getMusiquePath(id);

        return VideoPath;
    }

    public List<Musique> listMusiques(){return fichiersDao.listMusiques();}

    public Musique getMusique(Integer id) {
        return fichiersDao.getMusique(id);
    }

    public Musique addMusique(Musique musique, Part fileMusic) {

        // on génère un nombre aléatoire afin de ne pas avoir de doublons dans la base de données
        String filename = UUID.randomUUID().toString().substring(0,8) + "-" + fileMusic.getSubmittedFileName();
        Path MusicPath = null;

        if (fileMusic!=null) {
            MusicPath = Paths.get(MUSIC_DIRECTORY_PATH, filename);
            try {
                Files.copy(fileMusic.getInputStream(), MusicPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fichiersDao.addMusique(musique, MusicPath);
    }

    public void supprimerMusique(Integer id){fichiersDao.supprimerMusique(id);}

}
