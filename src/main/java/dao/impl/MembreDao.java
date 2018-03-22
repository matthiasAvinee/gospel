package dao.impl;

import entities.Membre;

import java.util.List;
import java.util.Map;


/**
 * Interface définissant MembreDaoImpl, Explication des fonctions sur la page MembreDaoImpl
 */

public interface MembreDao {
    public List<Membre> listMembres();

    public Membre getmembre(Integer id);

    public Membre addMembre(Membre membre);

    public Membre updateMembre (int id, String pseudo, String role);

    public Membre modifierMdp(int id, String mdp);

    void supprimermembre(int id);

    public Map<String, String> listAdminAutorises();

    public Map<String, String> listMembresAutorises();


}
