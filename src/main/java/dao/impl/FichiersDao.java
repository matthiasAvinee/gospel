package dao.impl;

import entities.*;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface définissant FichierDaoImpl, Explication des fonctions sur la page FichierDaoImpl
 */

public interface FichiersDao {

    public List<Album> listAlbums();

    public Album getAlbum(Integer id);

    public void addAlbum(String nomAlbum);

    public void supprimerAlbum(Integer id);

    public List<Photo> listPhotos(Integer id);

    public Photo getPhoto(Integer id);

    public Photo addPhoto(Photo photo, Path path);

    public void supprimerPhoto(Integer id);

    public List<PDF> listPDF();

    public PDF getPDF(Integer id);

    public PDF addPDF (PDF pdf, Path path);

    public void supprimerPDF (Integer id);

    public List<Video> listVideos();

    public Video getVideo (Integer id);

    public Video addVideo (Video video, Path path);

    public void supprimerVideo (Integer id);

}
