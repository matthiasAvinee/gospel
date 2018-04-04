package dao.impl.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("sys");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        }
        return dataSource;
    }

  /*  private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("eu-cdbr-west-02.cleardb.net");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("heroku_9fc24712e36bb44");
            dataSource.setUser("b34c4abb5e8eac");
            dataSource.setPassword("01cd2437");
        }
        return dataSource;
    }*/
}
