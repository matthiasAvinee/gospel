package manager;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dao.impl.impl.FichiersDaoImpl;
import entities.Album;
import entities.Photo;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


/**
 * Explication des fonctions sur FichiertDaoImpl
 */

public class FichiersBibliotheque {

    private static final String IMAGE_DIRECTORY_PATH = "C:/imagesDevWeb";

    /*Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "hd81lyvul", "hd81lyvul",
            "643872995173373", "643872995173373",
            "VUa867jRuuuZJ9bNqLslfwXAAGg", "VUa867jRuuuZJ9bNqLslfwXAAGg"));*/

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
            String filename = UUID.randomUUID().toString().substring(0,8) + "-" + picture.getSubmittedFileName();
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
