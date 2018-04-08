package dao.impl;

import entities.Paragraphe;

import javax.servlet.http.Part;
import java.nio.file.Path;
import java.util.List;

/**
 * Interface définissant ParagrapheDaoImpl, Explication des fonctions sur la page ParagrapheDaoImpl
 */

public interface ParagrapheDao {

    public List<Paragraphe> listParagraphesAcceuil();

    public List<Paragraphe> listParagraphesContact();


    public Paragraphe getparagraphe(Integer idBalise);

    public Paragraphe addParagraphe(Paragraphe paragraphe);

    public Paragraphe updateParagraphe(Integer idBalise, String titre, String texte, String page, int ordre);

    void supprimerParagraphe(Integer idBalise);

  
}
