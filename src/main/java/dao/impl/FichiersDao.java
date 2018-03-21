package dao.impl;

import entities.Album;
import entities.Photo;

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

    public Photo addPhoto(Photo photo, Path picturePath);

    public void supprimerPhoto(Integer id);

}
