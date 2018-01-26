package entities;

public class Membre {

    private Integer id;
    private String pseudo;
    private String mdp;
    private String role;

    public Membre(Integer id, String pseudo, String mdp, String role) {
        this.id = id;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
