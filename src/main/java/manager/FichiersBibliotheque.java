package manager;

import dao.impl.impl.FichiersDaoImpl;
import entities.Album;
import entities.Photo;
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

}
