package manager;

import dao.impl.EvenementDao;
import dao.impl.impl.EvenementDaoImpl;
import entities.Evenement;

import java.sql.Date;
import java.util.List;

/**
 * Explication des fonctions sur EvenementDaoImpl
 */


public class EvenementLibrary {

        private static class evenementLibraryHolder {
            private final static EvenementLibrary instance = new EvenementLibrary();
        }

        public static EvenementLibrary getInstance() {
            return evenementLibraryHolder.instance;
        }

        private EvenementDao evenementDao = new EvenementDaoImpl();


        private EvenementLibrary() {
        }



        public List<Evenement> listEvenementAvant(String date) {
            return evenementDao.listEvenementsAvant(date);
        }

        public List<Evenement> listEvenementApres(String date) {
        return evenementDao.listEvenementsApres(date);
    }

        public Evenement getEvenement(Integer id) {
            return evenementDao.getEvenement(id);
        }

        public Evenement addEvenement(Evenement evenement) {
            if (evenement == null) {
                throw new IllegalArgumentException("Le membre ne doit pas être vide");
            }

            return evenementDao.addEvenement(evenement);
        }

        public Evenement updateEvenement(int id, Double prix, String nom, String adresse, String description, String date) {
            return evenementDao.updateEvenement(id, prix, nom, adresse, description, date);
        }

        public void supprimerevenement(int id) {
            evenementDao.supprimerEvenement(id);
        }


    }

