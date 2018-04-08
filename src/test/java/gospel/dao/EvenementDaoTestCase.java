package gospel.dao;

import dao.impl.EvenementDao;
import entities.Paragraphe;
import manager.EvenementLibrary;
import manager.ParagrapheLibrary;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class EvenementDaoTestCase extends AbstractDaoTestCase {


    @Override
    public void insertDataSet(Statement statement) throws Exception {
        statement.executeUpdate("INSERT INTO evenement(id, prix, nom, description,adresse,date) VALUES(1, 5.5, 'Concert 1','Trop cool','2018-05-01')");
        statement.executeUpdate("INSERT INTO evenement(id, prix, nom, description,adresse,date) VALUES(2, 2, 'Concert 2','Trop cool','2018-05-01')");
        statement.executeUpdate("INSERT INTO evenement(id, prix, nom, description,adresse,date) VALUES(3, 0, 'Concert 3','Trop cool','2018-05-01')");
    }


}
