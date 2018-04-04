package dao.impl.impl;

import dao.impl.FichiersDao;
import entities.Album;
import entities.Photo;

import javax.xml.crypto.Data;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void addAlbum(String nomAlbum) {
        String query = "INSERT INTO album(nom_album) VALUES(?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nomAlbum);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String query = "SELECT * FROM photo JOIN album ON photo.album_id_fk = album.album_id WHERE photo.album_id_fk=? ORDER BY photo_id";
        List<Photo> listOfPhotos = new ArrayList<>();

        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        listOfPhotos.add(new Photo(resultSet.getInt("photo_id"),
                                new Album(resultSet.getInt("album_id"),
                                        resultSet.getString("nom_album"))));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPhotos;
    }

    @Override
    public List<Photo> listCouvertures() {
        String query = "SELECT * , MIN(album_id) AS premiere_photo FROM photo JOIN album ON photo.album_id_fk = album.album_id ORDER BY album_id_fk";
        List<Photo> listOfCouvertures = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfCouvertures.add
                        (new Photo(resultSet.getInt("premiere_photo"),
                                new Album(resultSet.getInt("album_id"),
                                        resultSet.getString("nom_album"))));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfCouvertures;
    }

    @Override
    public Photo getPhoto(Integer id) {
        String query = "SELECT * FROM photo WHERE photo_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return  new Photo(resultSet.getInt("photo_id"),
                            getAlbum(resultSet.getInt("album_id_fk")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Path getPicturePath(Integer id){
        String query = "SELECT chemin FROM photo WHERE photo_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    String picturePath = resultSet.getString("chemin");
                    if (picturePath!=null){
                        return Paths.get(picturePath);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Photo addPhoto(Photo photo, Path picturePath) {
        String query = "INSERT INTO photo (chemin, album_id_fk) VALUES (?,?)";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ){
            if (picturePath!=null){
                statement.setString(1, picturePath.toString());
            }else {
                statement.setNull(1,Types.VARCHAR);
            }
            statement.setInt(2, photo.getAlbum().getIdAlbum());

            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    photo.setIdPhoto(generatedId);
                    return photo;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerPhoto(Integer id) {
        String query = "DELETE FROM photo WHERE photo_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
