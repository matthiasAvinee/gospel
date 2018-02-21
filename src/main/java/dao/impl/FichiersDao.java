package dao.impl;

import entities.Album;
import entities.Photo;

import java.nio.file.Path;
import java.util.List;

public interface FichiersDao {

    public List<Album> listAlbums();

    public Album getAlbum(Integer id);

    public Album addAlbum(Album album);

    public void supprimerAlbum(Integer id);

    public List<Photo> listPhotos(Integer id);

    public Photo addPhoto(Photo photo);

    public void supprimerPhoto(Integer id);

}
