package dao.impl.impl;

import dao.impl.MembreDao;
import entities.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MembreDaoImpl implements MembreDao {

    /**
     *Permet de générer la liste des membres à partir de la BDD
     * @return la liste des membres
     */

    @Override
    public List<Membre> listMembres() {
        String query = "SELECT * FROM membre WHERE id>1 ORDER BY id";
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
                                resultSet.getString("mdp"),
                                resultSet.getString("role")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfMembres;
    }

    /**
     * Permet de récuperer un objet Membre de la BDD
     * @param id du membre que l'on veux récuperer
     * @return le membre ainsi choisi
     */
    @Override
    public Membre getmembre(Integer id) {
        String query = "SELECT * FROM membre WHERE id=?";
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

    /**
     * Permet de récuperer le mot de passe crypté dans la BDD
     * @param pseudo de l'utilisateur
     * @return le mot de passe stocké dans la base de donnée
     */
    public String getMotdePasse(String pseudo) {
        String query = "SELECT mdp FROM membre WHERE pseudo=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("mdp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * permet d'ajouter un membre  dans la BDD
     * @param membre, instance de Membre que l'on veut ajouter
     * @return le membre crée
     */
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
                if (ids.next()) {
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

    /**
     * Permet de modifier les informations d'un membre dans la BDD
     * @param id id du membre que l'on veut modifier
     * @param pseudo nouveau pseudo
     * @param role nouveau rôle
     * @return null
     */
    @Override
    public Membre updateMembre(int id, String pseudo, String role) {
        String query = "UPDATE membre SET pseudo=?,role=? WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, pseudo);
            statement.setString(2, role);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet de modifier le mot de passe d'un membre de la BDD
     * @param id id du membre dont on veut modifier le mot de passe
     * @param mdp le nouveau mot de passe que l'on veut donner
     * @return null
     */
    @Override
    public Membre modifierMdp(int id, String mdp) {
        String query = "UPDATE membre SET mdp=? WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, mdp);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Permet de supprimer un membre de la BDD
     * @param id id du membre que l'on veut supprimer
     */
    public void supprimermembre(int id){
            try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "delete from membre where id=?")) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * Permet d'obtenir liste de deux valeurs, pseudo et mdp, à partir de la bdd qui sont des administrateurs
     * @return
     */
    @Override
    public Map<String, String> listAdminAutorises() {

            Map<String, String> list = new HashMap<>();

            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT pseudo,mdp FROM membre WHERE role='administrateur'")) {

                while (resultSet.next()) {

                    list.put(resultSet.getString("pseudo"), resultSet.getString("mdp"));
                }
            } catch (SQLException e) {

            }

            return list;
        }

    /**
     * Permet d'obtenir liste de deux valeurs, pseudo et mdp, à partir de la bdd qui sont des membres
     * @return
     */

    @Override
    public Map<String, String> listMembresAutorises() {
        Map<String, String> list = new HashMap<>();

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT pseudo,mdp FROM membre WHERE role='membre'")) {

            while (resultSet.next()) {

                list.put(resultSet.getString("pseudo"), resultSet.getString("mdp"));
            }
        } catch (SQLException e) {

        }

        return list;
    }



}




