package manager;

import dao.impl.ParagrapheDao;
import dao.impl.impl.ParagrapheDaoImpl;
import entities.Paragraphe;

import javax.servlet.http.Part;
import java.util.List;

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

    public List<Paragraphe> listParagraphes() {
        return paragrapheDao.listParagraphes();
    }

    public Paragraphe getParagraphe(String id) {
        return paragrapheDao.getparagraphe(id);
    }

    public Paragraphe addParagraphe(Paragraphe paragraphe) {
        if (paragraphe == null) {
            throw new IllegalArgumentException("Le membre ne doit pas être vide");
        }
        if (paragraphe.getTexte() == null && paragraphe.getTitre()==null && paragraphe.getImg()==null ) {
            throw new IllegalArgumentException("Le pseudo ne doit pas être vide");
        }


        return paragrapheDao.addParagraphe(paragraphe);
    }

    public Paragraphe updateParagraphe(String id, String titre, String texte, Part img) {
        return paragrapheDao.updateParagraphe(id, titre, texte,img);
    }



    public void supprimermembre(String id) {
        paragrapheDao.supprimerParagraphe(id);
    }


}
