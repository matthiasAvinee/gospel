package dao.impl;

import entities.Evenement;

import java.sql.Date;
import java.util.List;

public interface EvenementDao {

    public List<Evenement> listEvenement();

    public List<Evenement> listEvenementsAvant(Date date);

    public List<Evenement> listEvenementsApres(Date date);

    public Evenement getEvenement(Integer id);

    public Evenement addEvenement(Evenement evenement);

    public Evenement updateEvenement (int id, int prix, String nom, String adresse, String description, Date date);

    void supprimerEvenement(int id);


}
