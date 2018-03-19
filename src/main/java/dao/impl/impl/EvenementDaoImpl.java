package dao.impl.impl;

import dao.impl.EvenementDao;
import entities.Evenement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementDaoImpl implements EvenementDao {
    @Override
    public List<Evenement> listEvenementsAvant(Date date) {
        String query = "SELECT * FROM evenement WHERE date<? ORDER BY id";
        List<Evenement> listOfevenements = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfevenements.add(
                        new Evenement(
                                resultSet.getInt("id"),
                                resultSet.getInt("prix"),
                                resultSet.getString("nom"),
                                resultSet.getString("adresse"),
                                resultSet.getString("description"),
                                resultSet.getDate("date")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfevenements;
    }


    @Override
    public List<Evenement> listEvenementsApres(Date date) {
        String query = "SELECT * FROM evenement WHERE date>? ORDER BY id";
        List<Evenement> listOfevenements = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfevenements.add(
                        new Evenement(
                                resultSet.getInt("id"),
                                resultSet.getInt("prix"),
                                resultSet.getString("nom"),
                                resultSet.getString("adresse"),
                                resultSet.getString("description"),
                                resultSet.getDate("date")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfevenements;
    }

    @Override
    public Evenement getEvenement(Integer id) {
        String query = "SELECT * FROM membre WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Evenement(
                            resultSet.getInt("id"),
                            resultSet.getInt("prix"),
                            resultSet.getString("nom"),
                            resultSet.getString("adresse"),
                            resultSet.getString("description"),
                            resultSet.getDate("date")

                            );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Evenement addEvenement(Evenement evenement) {
        String query = "INSERT INTO evenement(prix,nom,adresse,description,date) VALUES(?, ?, ?,?,?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, evenement.getPrix());
            statement.setString(2, evenement.getNom());
            statement.setString(3, evenement.getAdresse());
            statement.setString(4, evenement.getDescription());
            statement.setDate(5, evenement.getDate());
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

    @Override
    public Evenement updateEvenement(int id, int prix, String nom, String adresse, String description, Date date) {
        return null;
    }

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
