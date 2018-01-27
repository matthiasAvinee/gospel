package manager;

import dao.impl.impl.MembreDaoImpl;
import dao.impl.MembreDao;
import entities.Membre;

import java.util.List;

public class MembreLibrary {
    private static class membreLibraryHolder {
        private final static MembreLibrary instance = new MembreLibrary();
    }

    public static MembreLibrary getInstance() {
        return membreLibraryHolder.instance;
    }

    private MembreDao membreDao = new MembreDaoImpl();


    private MembreLibrary() {
    }

    public List<Membre> listMembres() {
        return membreDao.listMembres();
    }

    public Membre getMembre(Integer id) {
        return membreDao.getmembre(id);
    }

    public Membre addMembre(Membre membre) {
        if (membre == null) {
            throw new IllegalArgumentException("Le membre ne doit pas être vide");
        }
        if (membre.getPseudo() == null || "".equals(membre.getPseudo())) {
            throw new IllegalArgumentException("Le pseudo ne doit pas être vide");
        }
        if (membre.getRole() == null || "".equals(membre.getRole())) {
            throw new IllegalArgumentException("Le pseudo ne doit pas être vide");
        }

        return membreDao.addMembre(membre);
    }

    public Membre updateMembre(int id, String pseudo, String role) {
        return membreDao.updateMembre(id, pseudo, role);
    }

    public Membre modifierMdp(int id, String mdp) {
        return membreDao.modifierMdp(id, mdp);
    }

    public void supprimermembre(int id) {
        membreDao.supprimermembre(id);
    }
}
