package dao.impl.impl;

import dao.impl.FichiersDao;
import entities.*;

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
    public Photo getPhoto(Integer id) {
        String query = "SELECT * FROM photo JOIN album ON photo.album_id_fk = album.album_id WHERE photo_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return  new Photo(resultSet.getInt("photo_id"),
                            new Album(resultSet.getInt("album_id"),
                                    resultSet.getString("nom_album")));
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

    public Photo addPhoto(Photo photo, Path path) {
        String query = "INSERT INTO photo (chemin, album_id_fk) VALUES (?,?)";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ){
            if (path!=null){
                statement.setString(1, path.toString());
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

    /**
     *
     * @return
     */
    @Override
    public List<PDF> listPDF() {
        String query = "SELECT * FROM pdf ORDER BY pdf_id";
        List<PDF> listOfPDF = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfPDF.add
                        (new PDF(resultSet.getInt("pdf_id"),
                                resultSet.getString("pdf_nom")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfPDF;
    }

    @Override
    public PDF getPDF(Integer id) {
        String query = "SELECT * FROM pdf WHERE pdf_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return  new PDF(resultSet.getInt("pdf_id"),
                            resultSet.getString("pdf_nom"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Path> listPathPDF() {
        String query = "SELECT pdf_chemin FROM pdf ORDER BY pdf_id";
        List<Path> listOfPathPDF = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfPathPDF.add(Paths.get(resultSet.getString("pdf_chemin")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfPathPDF;
    }

    public Path getPDFPath(Integer id){
        String query = "SELECT pdf_chemin FROM pdf WHERE pdf_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    String pdfPath = resultSet.getString("pdf_chemin");
                    if (pdfPath!=null){
                        return Paths.get(pdfPath);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PDF addPDF(PDF pdf, Path path) {
        String query = "INSERT INTO pdf (pdf_chemin, pdf_nom) VALUES (?,?)";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ){
            if (path!=null){
                statement.setString(1, path.toString());
            }else {
                statement.setNull(1,Types.VARCHAR);
            }
            statement.setString(2, pdf.getNomPDF());

            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    pdf.setIdPDF(generatedId);
                    return pdf;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerPDF(Integer id) {
        String query = "DELETE FROM pdf WHERE pdf_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Vidéos
     *
     */

    public List<Video> listVideos() {
        String query = "SELECT * FROM video ORDER BY video_id";
        List<Video> listOfVideos = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfVideos.add
                        (new Video(resultSet.getInt("video_id"),
                                resultSet.getString("video_nom")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfVideos;
    }

    public Video getVideo(Integer id) {
        String query = "SELECT * FROM video WHERE video_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return  new Video(resultSet.getInt("video_id"),
                            resultSet.getString("video_nom"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Path> listPathVideos() {
        String query = "SELECT video_chemin FROM video ORDER BY video_id";
        List<Path> listOfPathVideo = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfPathVideo.add(Paths.get(resultSet.getString("video_chemin")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfPathVideo;
    }

    public Path getVideoPath(Integer id){
        String query = "SELECT video_chemin FROM video WHERE video_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    String videoPath = resultSet.getString("video_chemin");
                    if (videoPath!=null){
                        return Paths.get(videoPath);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Video addVideo(Video video, Path path) {
        String query = "INSERT INTO video (video_chemin, video_nom) VALUES (?,?)";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ){
            if (path!=null){
                statement.setString(1, path.toString());
            }else {
                statement.setNull(1,Types.VARCHAR);
            }
            statement.setString(2, video.getNomVideo());

            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    video.setIdVideo(generatedId);
                    return video;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void supprimerVideo(Integer id) {
        String query = "DELETE FROM video WHERE video_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Musiques
     *
     */

    public List<Path> listPathMusiques() {
        String query = "SELECT musique_chemin FROM musique ORDER BY musique_id";
        List<Path> listOfPathMusiques = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfPathMusiques.add(Paths.get(resultSet.getString("musique_chemin")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfPathMusiques;
    }

    public Path getMusiquePath(Integer id){
        String query = "SELECT musique_chemin FROM musique WHERE musique_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    String musiquePath = resultSet.getString("musique_chemin");
                    if (musiquePath!=null){
                        return Paths.get(musiquePath);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Musique> listMusiques() {
        String query = "SELECT * FROM musique ORDER BY musique_id";
        List<Musique> listOfMusiques = new ArrayList<>();

        try(
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                listOfMusiques.add
                        (new Musique(resultSet.getInt("musique_id"),
                                resultSet.getString("musique_nom")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return listOfMusiques;
    }

    @Override
    public Musique getMusique(Integer id) {
        String query = "SELECT * FROM musique WHERE musique_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return  new Musique(resultSet.getInt("musique_id"),
                            resultSet.getString("musique_nom"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Musique addMusique(Musique musique, Path path) {
        String query = "INSERT INTO musique (musique_chemin, musique_nom) VALUES (?,?)";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ){
            if (path!=null){
                statement.setString(1, path.toString());
            }else {
                statement.setNull(1,Types.VARCHAR);
            }
            statement.setString(2, musique.getNomMusique());

            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    musique.setIdMusique(generatedId);
                    return musique;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerMusique(Integer id) {
        String query = "DELETE FROM musique WHERE musique_id=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
