package dao.impl;

import entities.Paragraphe;

import javax.servlet.http.Part;
import java.util.List;

public interface ParagrapheDao {

    public List<Paragraphe> listParagraphesAcceuil();

    public List<Paragraphe> listParagraphesContact();

    public Paragraphe getparagraphe(Integer idBalise);

    public Paragraphe addParagraphe(Paragraphe paragraphe);

    public Paragraphe updateParagraphe(Integer idBalise, String titre, String texte, String chemin, int ordre);

    void supprimerParagraphe(Integer idBalise);

}
