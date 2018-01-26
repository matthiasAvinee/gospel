package dao.impl.impl;

import dao.impl.MembreDao;
import entities.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {


    @Override
    public List<Membre> listMembres() {
        String query = "SELECT * FROM membre ORDER BY pseudo";
        List<Membre> listOfMembres = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfMembres.add(
                        new Membre(
                                resultSet.getInt("id"),
                                resultSet.getString("pseudo"),
                                resultSet.getString("role"),
                                resultSet.getString("mdp"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfMembres;
    }


    @Override
    public Membre getmembre(Integer id) {
        String query = "SELECT * FROM membre WHERE film_id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Membre(
                            resultSet.getInt("id"),
                            resultSet.getString("pseudo"),
                            resultSet.getString("mdp"),
                            resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Membre addMembre(Membre membre) {
        String query = "INSERT INTO membre(pseudo,mdp,role) VALUES(?, ?, ?)";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, membre.getPseudo());
            statement.setString(2, membre.getMdp());
            statement.setString(3, membre.getRole());
            statement.executeUpdate();

            try (ResultSet ids = statement.getGeneratedKeys()) {
                if(ids.next()) {
                    int generatedId = ids.getInt(1);
                    membre.setId(generatedId);
                    return membre;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    }



