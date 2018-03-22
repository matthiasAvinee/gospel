package manager;

import dao.impl.FichiersDao;
import dao.impl.impl.FichiersDaoImpl;
import entities.Album;
import entities.Photo;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Explication des fonctions sur FichiertDaoImpl
 */

public class FichiersBibliotheque {

    private static final String IMAGE_DIRECTORY_PATH = "C:/imagesDevWeb";

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

    public Photo addPhoto(Photo photo, Part picture) {

        Path picturePath = null;
        if (picture!=null) {
            picturePath = Paths.get(IMAGE_DIRECTORY_PATH, picture.getSubmittedFileName());
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
