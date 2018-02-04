package dao.impl.impl;

import dao.impl.ParagrapheDao;
import entities.Paragraphe;

import javax.servlet.http.Part;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParagrapheDaoImpl implements ParagrapheDao {
    @Override
    public List<Paragraphe> listParagraphesAcceuil() {
        String query = "SELECT * FROM paragraphe  WHERE page='home' ORDER BY ordre";
        List<Paragraphe> listOfParagraphes = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfParagraphes.add(
                        new Paragraphe(
                                resultSet.getInt("id"),
                                resultSet.getString("titre"),
                                resultSet.getString("texte"),
                                (Part) resultSet.getBlob("img"),
                                resultSet.getString("page"),
                                resultSet.getInt("ordre")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfParagraphes;
    }
    @Override
    public List<Paragraphe> listParagraphesContact() {
        String query = "SELECT * FROM paragraphe WHERE page='contacter' ORDER BY ordre ";
        List<Paragraphe> listOfParagraphes = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfParagraphes.add(
                        new Paragraphe(
                                resultSet.getInt("id"),
                                resultSet.getString("titre"),
                                resultSet.getString("texte"),
                                (Part) resultSet.getBlob("img"),
                                resultSet.getString("page"),
                                resultSet.getInt("ordre")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfParagraphes;
    }

    @Override
    public Paragraphe getparagraphe(Integer idBalise) {
        String query = "SELECT * FROM paragraphe WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idBalise);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Paragraphe(
                            resultSet.getInt("id"),
                            resultSet.getString("titre"),
                            resultSet.getString("texte"),
                            (Part) resultSet.getBlob("img"),
                            resultSet.getString("page"),
                            resultSet.getInt("ordre")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paragraphe addParagraphe(Paragraphe paragraphe) {
        String query = "INSERT INTO paragraphe(titre,texte,img,page) VALUES(?, ?, ?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, paragraphe.getTitre());
            statement.setString(2, paragraphe.getTexte());
            statement.setBlob(3, (Blob) paragraphe.getImg());
            statement.setString(4,paragraphe.getPage());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    paragraphe.setIdBalise(generatedId);
                    return paragraphe;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Paragraphe updateParagraphe(Integer idBalise, String titre, String texte, Part img, int ordre) {

        String query = "UPDATE paragraphe SET titre=?,texte=?,img=?,ordre=? WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, titre);
            statement.setString(2, texte);
            statement.setBlob(3, (Blob) img);
            statement.setInt(4,ordre);
            statement.setInt(5, idBalise);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerParagraphe(Integer idBalise) {

        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from paragraphe where id=?")) {
                statement.setInt(1, idBalise);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
