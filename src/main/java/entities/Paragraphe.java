package entities;

import javax.servlet.http.Part;

public class Paragraphe {

    String idBalise;
    String titre;
    String texte;
    Part img;


    public Paragraphe(String idBalise, String titre, String texte, Part img) {
        this.idBalise = idBalise;
        this.titre = titre;
        this.texte = texte;
        this.img = img;
    }

    public String getIdBalise() {
        return idBalise;
    }

    public void setIdBalise(String idBalise) {
        this.idBalise = idBalise;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Part getImg() {
        return img;
    }

    public void setImg(Part img) {
        this.img = img;
    }
}
