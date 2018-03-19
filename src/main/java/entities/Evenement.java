package entities;

import java.sql.Date;

public class Evenement {
    private int id;
    private int prix;
    private String nom;
    private String adresse;
    private String description;
    private Date date;

    public Evenement(int id, int prix, String nom, String adresse, String description, Date date) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
