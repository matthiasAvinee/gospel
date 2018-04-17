package entities;

public class PDF {

    Integer idPDF;
    String nomPDF;

    public PDF(Integer idPDF, String nomPDF) {
        this.idPDF = idPDF;
        this.nomPDF = nomPDF;
    }

    public Integer getIdPDF() {
        return idPDF;
    }

    public void setIdPDF(Integer idPDF) {
        this.idPDF = idPDF;
    }

    public String getNomPDF() {
        return nomPDF;
    }

    public void setNomPDF(String nomPDF) {
        this.nomPDF = nomPDF;
    }

}
