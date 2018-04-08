package manager;

import dao.impl.ParagrapheDao;
import dao.impl.impl.ParagrapheDaoImpl;
import entities.Paragraphe;

import javax.servlet.http.Part;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Explication des fonctions sur ParagrapheDaoImpl
 */

public class ParagrapheLibrary {

    private static class ParagrapheLibraryHolder {
        private final static ParagrapheLibrary instance = new ParagrapheLibrary();
    }

    public static ParagrapheLibrary getInstance() {
        return ParagrapheLibraryHolder.instance;
    }

    private ParagrapheDao paragrapheDao = new ParagrapheDaoImpl();


    private ParagrapheLibrary() {
    }

    public List<Paragraphe> listParagraphesAcceuil() {
        return paragrapheDao.listParagraphesAcceuil();
    }

    public List<Paragraphe> listParagraphesContact() {
        return paragrapheDao.listParagraphesContact();
    }

    public Paragraphe getParagraphe(Integer id) {
        return paragrapheDao.getparagraphe(id);
    }

    public Paragraphe addParagraphe(Paragraphe paragraphe) {
        if (paragraphe == null) {
            throw new IllegalArgumentException("Le membre ne doit pas être vide");
        }
        if (paragraphe.getTexte() == null && paragraphe.getTitre()==null ) {
            throw new IllegalArgumentException("Le pseudo ne doit pas être vide");
        }


        return paragrapheDao.addParagraphe(paragraphe);
    }

    public Paragraphe updateParagraphe(int id, String titre, String texte, String chemin, int ordre) {
        return paragrapheDao.updateParagraphe(id, titre, texte, chemin, ordre);
    }



    public void supprimerParagraphe(int id) {
        paragrapheDao.supprimerParagraphe(id);
    }






}
