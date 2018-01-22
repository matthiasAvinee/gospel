package manager;

import dao.impl.impl.MembreDaoImpl;
import dao.impl.MembreDao;
import entities.Membre;

import java.util.List;

public class membreLibrary {
    private static class membreLibraryHolder {
        private final static membreLibrary instance = new membreLibrary();
    }

    public static membreLibrary getInstance() {
        return membreLibraryHolder.instance;
    }

    private MembreDao membreDao = new MembreDaoImpl();


    private membreLibrary() {
    }

    public List<Membre> listFilms() {
        return membreDao.listMembres();
    }

    public Membre getFilm(Integer id) {
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
}
