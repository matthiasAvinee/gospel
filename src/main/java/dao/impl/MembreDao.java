package dao.impl;

import entities.Membre;

import java.util.List;

public interface MembreDao {
    public List<Membre> listMembres();

    public Membre getmembre(Integer id);

    public Membre addMembre(Membre membre);
}
