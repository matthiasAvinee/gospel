package gospel.dao;

import dao.impl.MembreDao;
import dao.impl.impl.DataSourceProvider;
import dao.impl.impl.MembreDaoImpl;
import entities.Membre;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MembreDaoTestCase extends AbstractDaoTestCase {
    private MembreDao membreDao = new MembreDaoImpl();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(1,'root','$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ','administrateur')");
        statement.executeUpdate("INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(2,'quentin','$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ','administrateur')");
        statement.executeUpdate("INSERT INTO `membre` (`id`,`pseudo`,`mdp`,`role`) VALUES(3,'matthias','$argon2i$v=19$m=65536,t=2,p=1$slv6QY6wp3yJytsnXb3TXg$6MK0sBb8jrp5BD2QUP/SbacltM5YwVHOYJ4ycFb74Z0','membre');");
    }

    @Test
    public void shouldListMembres() throws Exception {
        // WHEN
        List<Membre> membres = membreDao.listMembres();
        // THEN
        Assertions.assertThat(membres).hasSize(2); //Le compte root ne doit pas s'afficher, il ne doit donc pas apparaître ensuite
        Assertions.assertThat(membres).extracting("id", "pseudo", "mdp", "role").containsOnly(
                Assertions.tuple(2, "quentin", "$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ", "administrateur"),
                Assertions.tuple(3, "matthias", "$argon2i$v=19$m=65536,t=2,p=1$slv6QY6wp3yJytsnXb3TXg$6MK0sBb8jrp5BD2QUP/SbacltM5YwVHOYJ4ycFb74Z0", "membre")
        );
    }

    @Test
    public void shouldGetMembres() throws Exception {
        // WHEN
        Membre membre = membreDao.getmembre(1);
        // THEN
        Assertions.assertThat(membre).isNotNull();
        Assertions.assertThat(membre.getId()).isEqualTo(1);
        Assertions.assertThat(membre.getPseudo()).isEqualTo("root");
        Assertions.assertThat(membre.getRole()).isEqualTo("administrateur");
        Assertions.assertThat(membre.getMdp()).isEqualTo("$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ");

    }

    @Test
    public void shouldAddMembre() throws Exception {
        // GIVEN
        Membre newMembre = new Membre(null, "Test", "$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ", "administrateur");
        // WHEN
        membreDao.addMembre(newMembre);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM membre WHERE pseudo='Test'")) {
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("id")).isNotNull();
            Assertions.assertThat(resultSet.getString("pseudo")).isEqualTo("Test");
            Assertions.assertThat(resultSet.getString("mdp")).isEqualTo("$argon2i$v=19$m=65536,t=2,p=1$4BBf74e80ETLZiQT/WvCxw$bsZ1wJcaUY1OalxuMgvwXSKyf0LT+HhFp4M516lzlTQ");
            Assertions.assertThat(resultSet.getString("role")).isEqualTo("administrateur");
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldUpdateMembre() throws ExecutionException {
        //GIVEN
        membreDao.updateMembre(3, "matmout", "administrateur");
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM membre WHERE id=3")) {
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("id")).isEqualTo(3);
            Assertions.assertThat(resultSet.getString("pseudo")).isEqualTo("matmout");
            Assertions.assertThat(resultSet.getString("role")).isEqualTo("administrateur");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

