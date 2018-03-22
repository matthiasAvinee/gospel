package dao.impl.impl;

import dao.impl.ParagrapheDao;
import entities.Paragraphe;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ParagrapheDaoImpl implements ParagrapheDao {
    @Override


    /**
     * Permet de récuper la liste des paragraphes de la page d'acceuil à partir de la BDD
     */

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

    /**
     *Permet de récuper la liste des paragraphes de la page d'acceuil à partir de la BDD
     */
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

    /**
     * Permet de récuper un paragraphe
     * @param idBalise id du paragraphe que l'on veut récuperer
     * @return le paragraphe ayant pour id l'id passé en paramètre
     */
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

    /**
     * Permet d'ajouter un paragraphe dans la BDD
     * @param paragraphe le paragraphe que l'on veut ajouter
     * @return le paragraphe ajouté à la BDD
     */
    @Override
    public Paragraphe addParagraphe(Paragraphe paragraphe) {
        String query = "INSERT INTO paragraphe(titre,texte,page,ordre) VALUES(?, ?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, paragraphe.getTitre());
            statement.setString(2, paragraphe.getTexte());
            statement.setString(3, paragraphe.getPage());
            statement.setInt(4,paragraphe.getOrdre());
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

    /**
     * Permet de mettre à jour un paragraphe de la BSS
     * @param idBalise id du paragraphe que l'on veut modifier
     * @param titre nouveau titre que l'on veut donner
     * @param texte nouveau texte que l'on veut donner
     * @param ordre nouvel ordre que l'on veut donner
     * @return
     */
    @Override
    public Paragraphe updateParagraphe(Integer idBalise, String titre, String texte, int ordre) {

        String query = "UPDATE paragraphe SET titre=?,texte=?,ordre=? WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, titre);
            statement.setString(2, texte);
            statement.setInt(3, ordre);
            statement.setInt(4, idBalise);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet de supprimer un paragraphe de la BDD
     * @param idBalise id du paragraphe que l'on veut supprimer
     */
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
