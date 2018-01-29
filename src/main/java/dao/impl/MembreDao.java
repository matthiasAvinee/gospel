package dao.impl;

import entities.Membre;

import java.util.List;

public interface MembreDao {
    public List<Membre> listMembres();

    public Membre getmembre(Integer id);

    public Membre addMembre(Membre membre);

    public Membre updateMembre (int id, String pseudo, String role);

    public Membre modifierMdp(int id, String mdp);

    void supprimermembre(int id);
}
