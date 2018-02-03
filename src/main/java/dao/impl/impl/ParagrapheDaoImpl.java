package dao.impl.impl;

import dao.impl.ParagrapheDao;
import entities.Paragraphe;

import javax.servlet.http.Part;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParagrapheDaoImpl implements ParagrapheDao {
    @Override
    public List<Paragraphe> listParagraphes() {
        String query = "SELECT * FROM paragraphe ORDER BY id";
        List<Paragraphe> listOfParagraphes = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfParagraphes.add(
                        new Paragraphe(
                                resultSet.getString("id"),
                                resultSet.getString("titre"),
                                resultSet.getString("texte"),
                                (Part) resultSet.getBlob("img")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfParagraphes;
    }

    @Override
    public Paragraphe getparagraphe(String idBalise) {
        String query = "SELECT * FROM paragraphe WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, idBalise);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Paragraphe(
                            resultSet.getString("id"),
                            resultSet.getString("titre"),
                            resultSet.getString("texte"),
                            (Part) resultSet.getBlob("img"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paragraphe addParagraphe(Paragraphe paragraphe) {
        String query = "INSERT INTO paragraphe(id,titre,texte,img) VALUES(?,?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, paragraphe.getIdBalise());
            statement.setString(2, paragraphe.getTitre());
            statement.setString(3, paragraphe.getTexte());
            statement.setBlob(4, (Blob) paragraphe.getImg());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {


                    return paragraphe;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paragraphe updateParagraphe(String idBalise, String titre, String texte, Part img) {

        String query = "UPDATE paragraphe SET titre=?,texte=?,img=? WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, titre);
            statement.setString(2, texte);
            statement.setBlob(3, (Blob) img);
            statement.setString(4, idBalise);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerParagraphe(String idBalise) {

        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from paragraphe where id=?")) {
                statement.setString(1, idBalise);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
