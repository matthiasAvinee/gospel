package entities;

import javax.servlet.http.Part;

public class Paragraphe {

    Integer idBalise;
    String titre;
    String texte;
    Part img;
    String page;
    Integer ordre;

    public Paragraphe(Integer idBalise, String titre, String texte, Part img, String page, Integer ordre) {
        this.idBalise = idBalise;
        this.titre = titre;
        this.texte = texte;
        this.img = img;
        this.page = page;
        this.ordre = ordre;
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }


    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Integer getIdBalise() {
        return idBalise;
    }

    public void setIdBalise(Integer idBalise) {
        this.idBalise = idBalise;
    }
}

