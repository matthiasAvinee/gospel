package gospel.dao;

import dao.impl.FichiersDao;
import dao.impl.impl.DataSourceProvider;
import dao.impl.impl.FichiersDaoImpl;
import entities.Album;
import entities.Photo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FichiersDaoTestCase extends AbstractDaoTestCase {


        private FichiersDao fichierDao = new FichiersDaoImpl();

        @Override
        public void insertDataSet(Statement statement) throws Exception {
            statement.executeUpdate("INSERT INTO `album` (`album_id`,`nom_album`) VALUES(1,'album 1')");
            statement.executeUpdate("INSERT INTO `album` (`album_id`,`nom_album`) VALUES(2,'album 2')");
            statement.executeUpdate("INSERT INTO `album` (`album_id`,`nom_album`) VALUES(3,'album 3')");
            statement.executeUpdate("INSERT INTO `photo` (`photo_id`,`chemin`,`album_id_fk`) VALUES(1,'C:mesImages.image1',1)");
            statement.executeUpdate("INSERT INTO `photo` (`photo_id`,`chemin`,`album_id_fk`) VALUES(2,'C:mesImages.image2',1)");
            statement.executeUpdate("INSERT INTO `photo` (`photo_id`,`chemin`,`album_id_fk`) VALUES(3,'C:mesImages.image3',2)");
        }

        //permiere partie des test, les albums

        @Test
        public void shouldListAlbums() throws Exception {
            // WHEN
            List<Album> album = fichierDao.listAlbums();
            // THEN
            Assertions.assertThat(album).hasSize(3);
            Assertions.assertThat(album).extracting("idAlbum", "nomAlbum").containsOnly(
                    Assertions.tuple(1, "album 1"),
                    Assertions.tuple(2, "album 2"),
                    Assertions.tuple(3, "album 3")
                        );
        }

        @Test
        public void shouldGetAlbum() throws Exception {
            // WHEN
            Album album =fichierDao.getAlbum(1);
            // THEN
            Assertions.assertThat(album).isNotNull();
            Assertions.assertThat(album.getIdAlbum()).isEqualTo(1);
            Assertions.assertThat(album.getNomAlbum()).isEqualTo("album 1");

        }

        @Test
        public void shouldAddAlbum() throws Exception {


            // WHEN
            fichierDao.addAlbum("Test");
            // THEN
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM album WHERE nom_album='Test'")) {
                Assertions.assertThat(resultSet.next()).isTrue();
                Assertions.assertThat(resultSet.getInt("album_id")).isNotNull();
                Assertions.assertThat(resultSet.getString("nom_album")).isEqualTo("Test");
                Assertions.assertThat(resultSet.next()).isFalse();
            }
        }





    }

