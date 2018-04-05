package dao.impl.impl;

import dao.impl.EvenementDao;
import entities.Evenement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementDaoImpl implements EvenementDao {



    /**
     * Cette fonction crée une liste d'événements à partir de la BDD
     * @param date La date du jour
     * @return la liste des événements antérieurs à la date placée en paramètre
     */

    @Override
    public List<Evenement> listEvenementsAvant(String date) {
        List<Evenement> list = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM evenement WHERE date<? ORDER by date desc")) {

                statement.setString(1, date);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(new Evenement(
                                        resultSet.getInt("id"),
                                        resultSet.getDouble("prix"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("adresse"),
                                        resultSet.getString("description"),
                                        resultSet.getString("date")
                                )
                        );

                    }
                    return list;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return list;

    }

    /**
     * Cette fonction crée une liste d'événements à partir de la BDD
     * @param date La date du jour
     * @return la liste des événements posterieurs à la date placée en paramètre
     */
    @Override
    public List<Evenement> listEvenementsApres(String date) {

        List<Evenement> list = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM evenement WHERE date>=? order by date desc")) {

                statement.setString(1, date);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        list.add(new Evenement(
                                        resultSet.getInt("id"),
                                        resultSet.getDouble("prix"),
                                        resultSet.getString("nom"),
                                        resultSet.getString("adresse"),
                                        resultSet.getString("description"),
                                        resultSet.getString("date")
                                )
                        );

                    }
                    return list;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return list;
    }


    /**
     * Recupère un objet Evenement de la BDD à partir d'un id
     * @param id le n° d'id de l'événement dans la BDD
     * @return L'événement correspondant à l'id placé en paramètre
     */
    @Override
    public Evenement getEvenement(Integer id) {
        String query = "SELECT * FROM evenement WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Evenement(
                            resultSet.getInt("id"),
                            resultSet.getDouble("prix"),
                            resultSet.getString("nom"),
                            resultSet.getString("adresse"),
                            resultSet.getString("description"),
                            resultSet.getString("date")

                            );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *Permet d'ajouter un événement dans la BDD
     * @param evenement L'objet événement que l'on veux ajouter
     * @return l'événement crée
     */

    @Override
    public Evenement addEvenement(Evenement evenement) {
        String query = "INSERT INTO evenement(prix,nom,adresse,description,date) VALUES(?, ?, ?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, evenement.getPrix());
            statement.setString(2, evenement.getNom());
            statement.setString(3, evenement.getAdresse());
            statement.setString(4, evenement.getDescription());
            statement.setString(5, evenement.getDate());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if (ids.next()) {
                    int generatedId = ids.getInt(1);
                    evenement.setId(generatedId);
                    return evenement;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet de mettre à jour un Evenement dans la BDD
     * @param id de l'événement à modifier
     * @param prix la nouvelle valeur prix que l'on veut donner
     * @param nom la nouvelle valeur nom que l'on veut donner
     * @param adresse la nouvelle valeur adresse que l'on veut donner
     * @param description la nouvelle valeur description que l'on veut donner
     * @param date la nouvelle valeur prix que l'on date donner
     * @return null
     */
    @Override
    public Evenement updateEvenement(Integer id, Double prix, String nom, String adresse, String description, String date) {
        String query = "UPDATE evenement SET prix=?,nom=?, adresse=?, description=?, date=?  WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, prix);
            statement.setString(2, nom);
            statement.setString(3, adresse);
            statement.setString(4, description);
            statement.setString(5, date);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet de supprimer un événement de la BDD
     * @param id de l'élément à supprimer
     */
    @Override
    public void supprimerEvenement(int id) {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "delete from evenement where id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
