package gospel.dao;

import dao.impl.EvenementDao;
import dao.impl.impl.DataSourceProvider;
import dao.impl.impl.EvenementDaoImpl;
import entities.Evenement;
import entities.Paragraphe;
import manager.EvenementLibrary;
import manager.ParagrapheLibrary;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EvenementDaoTestCase extends AbstractDaoTestCase {
    private EvenementDao evenementDao = new EvenementDaoImpl();

    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO evenement(id, prix, nom, description,adresse,date) VALUES(1, 5.5, 'Concert 1','Trop cool','2 rue du port',NULL )");
        statement.executeUpdate("INSERT INTO evenement(id, prix, nom, description,adresse,date) VALUES(2, 2, 'Concert 2','Trop cool','2 rue du port',NULL )");
        statement.executeUpdate("INSERT INTO evenement(id, prix, nom, description,adresse,date) VALUES(3, 0, 'Concert 3','Trop cool','2 rue du port',NULL )");
    }


    @Test
    public void shouldGetEvenements() throws Exception {
        // WHEN
        Evenement evenement = evenementDao.getEvenement(1);
        // THEN
        Assertions.assertThat(evenement).isNotNull();
        Assertions.assertThat(evenement.getId()).isEqualTo(1);
        Assertions.assertThat(evenement.getPrix()).isEqualTo(5.5);
        Assertions.assertThat(evenement.getNom()).isEqualTo("Concert 1");
        Assertions.assertThat(evenement.getAdresse()).isEqualTo("2 rue du port");
        Assertions.assertThat(evenement.getDescription()).isEqualTo("Trop cool");

    }

    @Test
    public void shouldAddEvenement() throws Exception {
        // GIVEN
        Evenement newEvenement = new Evenement(null, 10.0, "Concert test", "5 rue de la paix","Venez dancer",null);
        // WHEN
        evenementDao.addEvenement(newEvenement);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM evenement WHERE nom='Concert test'")) {
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("id")).isNotNull();
            Assertions.assertThat(resultSet.getString("nom")).isEqualTo("Concert test");
            Assertions.assertThat(resultSet.getString("adresse")).isEqualTo("5 rue de la paix");
            Assertions.assertThat(resultSet.getString("description")).isEqualTo("Venez dancer");
            Assertions.assertThat(resultSet.getDouble("prix")).isEqualTo(10.0);
            Assertions.assertThat(resultSet.next()).isFalse();
        }
    }

    @Test
    public void shouldUpdateEvenement() throws ExecutionException {
        //GIVEN
        evenementDao.updateEvenement(1, 10.0, "Concert test", "5 rue de la paix","Venez dancer",null);
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM evenement WHERE id=1")) {
            Assertions.assertThat(resultSet.next()).isTrue();
            Assertions.assertThat(resultSet.getInt("id")).isEqualTo(1);
            Assertions.assertThat(resultSet.getString("nom")).isEqualTo("Concert test");
            Assertions.assertThat(resultSet.getString("adresse")).isEqualTo("5 rue de la paix");
            Assertions.assertThat(resultSet.getString("description")).isEqualTo("Venez dancer");
            Assertions.assertThat(resultSet.getDouble("prix")).isEqualTo(10.0);


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
