package gospel.dao;

import dao.impl.impl.DataSourceProvider;
import org.junit.Before;

import java.sql.Connection;
import java.sql.Statement;

public abstract class AbstractDaoTestCase {

    @Before
    public void initDatabase() throws Exception {
        try(
                Connection connection = DataSourceProvider.getDataSourceHorsConnection().getConnection();
                Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM evenement");
            statement.executeUpdate("DELETE FROM paragraphe");
            statement.executeUpdate("DELETE FROM album");
            statement.executeUpdate("DELETE FROM membre");
            statement.executeUpdate("DELETE FROM photo");

            this.insertDataSet(statement);
        }
    }

    public abstract void insertDataSet(Statement statement) throws Exception;
}

