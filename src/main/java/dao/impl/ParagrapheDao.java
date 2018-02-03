package dao.impl;

import entities.Paragraphe;

import javax.servlet.http.Part;
import java.util.List;

public interface ParagrapheDao {

    public List<Paragraphe> listParagraphes();

    public Paragraphe getparagraphe(String idBalise);

    public Paragraphe addParagraphe(Paragraphe paragraphe);

    public Paragraphe updateParagraphe(String idBalise, String titre, String texte, Part img);

    void supprimerParagraphe(String idBalise);

}
