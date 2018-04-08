package gospel.dao;

import dao.impl.ParagrapheDao;
import dao.impl.impl.DataSourceProvider;
import dao.impl.impl.ParagrapheDaoImpl;
import entities.Membre;
import entities.Paragraphe;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ParagrapheDaoTestCase extends AbstractDaoTestCase {


        ParagrapheDao paragrapheDao= new ParagrapheDaoImpl();

    @Override
        public void insertDataSet(Statement statement) throws Exception {
            statement.executeUpdate("INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(1,'Paragraphe 1','Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata','home',1)");
            statement.executeUpdate("INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(2,'Paragraphe 2','Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata','home',2)");
            statement.executeUpdate("INSERT INTO `paragraphe` (`id`,`titre`,`texte`,`page`,`ordre`) VALUES(3,'Paragraphe 3','Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata','contact',1)");
        }

        @Test
        public void shouldListParagraphes() throws Exception {
            // WHEN
            List<Paragraphe> paragraphesHome = paragrapheDao.listParagraphesAcceuil();
            List<Paragraphe> paragraphesContact = paragrapheDao.listParagraphesContact();
            // THEN
            Assertions.assertThat(paragraphesHome).hasSize(2);
            Assertions.assertThat(paragraphesHome).extracting("id", "pseudo", "mdp","role").containsOnly(
                    Assertions.tuple(1,"Paragraphe 1","Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata","home"),
                    Assertions.tuple(2,"Paragraphe 2","Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata","home")
            );
            Assertions.assertThat(paragraphesContact).extracting("id", "pseudo", "mdp","role").containsOnly(
                    Assertions.tuple(3,"Paragraphe 3","Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata","contact")

            );

        }

        @Test
        public void shouldGetParagraphes() throws Exception {
            // WHEN
            Paragraphe paragraphe = paragrapheDao.getparagraphe(1);
            // THEN
            Assertions.assertThat(paragraphe).isNotNull();
            Assertions.assertThat(paragraphe.getIdBalise()).isEqualTo(1);
            Assertions.assertThat(paragraphe.getPage()).isEqualTo("home");
            Assertions.assertThat(paragraphe.getTexte()).isEqualTo("Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata Patati patata");
            Assertions.assertThat(paragraphe.getOrdre()).isEqualTo("1");
            Assertions.assertThat(paragraphe.getTitre()).isEqualTo("Paragraphe 1");


        }

        @Test
        public void shouldAddParagraphe() throws Exception {
            // GIVEN
            Paragraphe newParagraphe = new Paragraphe(null, "Test", "Ceci est un test","home",3);
            // WHEN
            paragrapheDao.addParagraphe(newParagraphe);
            // THEN
            try(Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM paragraphe WHERE titre='Test'")){
                Assertions.assertThat(resultSet.next()).isTrue();
                Assertions.assertThat(resultSet.getInt("id")).isNotNull();
                Assertions.assertThat(resultSet.getString("titre")).isEqualTo("Test");
                Assertions.assertThat(resultSet.getString("texte")).isEqualTo("Ceci est un test");
                Assertions.assertThat(resultSet.getString("page")).isEqualTo("home");
                Assertions.assertThat(resultSet.getString("ordre")).isEqualTo("3");
                Assertions.assertThat(resultSet.next()).isFalse();
            }
        }
    }

