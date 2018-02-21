package dao.impl.impl;

import dao.impl.FichiersDao;
import entities.Album;
import entities.Photo;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichiersDaoImpl implements FichiersDao {
    @Override
    public List<Album> listAlbums() {
        String query = "SELECT * FROM album ORDER BY album_id";
        List<Album> listOfAlbums = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfAlbums.add
                        (new Album(resultSet.getInt("album_id"),
                                resultSet.getString("nom_album")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfAlbums;
    }

    @Override
    public Album getAlbum(Integer id) {
        String query = "SELECT * FROM album WHERE album_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return  new Album(resultSet.getInt("album_id"),
                            resultSet.getString("nom_album"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Album addAlbum(Album album) {
        String query = "INSERT INTO album(nom_album) VALUES(?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, album.getNomAlbum());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerAlbum(Integer id) {
        String query = "DELETE FROM album WHERE album_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Photo> listPhotos(Integer id) {
        String query = "SELECT * FROM photo ORDER BY photo_id WHERE album_id_fk=?";
        List<Photo> listOfPhotos = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfPhotos.add
                        (new Photo(resultSet.getInt("photo_id"),
                                resultSet.getString("chemin"),
                                new Album(resultSet.getInt("album_id_fk"))));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfPhotos;
    }

    @Override
    public Photo addPhoto(Photo photo) {
        return null;
    }

    @Override
    public void supprimerPhoto(Integer id) {

    }
}
