package dao.impl;

import entities.Evenement;

import java.sql.Date;
import java.util.List;


/**
 * Interface définissant EvenementDaoImpl, Explication des fonctions sur la page EvenementDaoImpl
 */
public interface EvenementDao {




    public List<Evenement> listEvenementsAvant(String date);

    public List<Evenement> listEvenementsApres(String date);

    public Evenement getEvenement(Integer id);

    public Evenement addEvenement(Evenement evenement);

    public Evenement updateEvenement (Integer id, Double prix, String nom, String adresse, String description, String date);

    void supprimerEvenement(int id);


}
