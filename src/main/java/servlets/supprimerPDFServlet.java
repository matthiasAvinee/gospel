package servlets;

import entities.PDF;
import manager.FichiersBibliotheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/administrateur/supprimer-pdf")
public class supprimerPDFServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pdfId = request.getParameter("id");

        PDF pdf = FichiersBibliotheque.getInstance().getPDF(Integer.parseInt(pdfId));

        File MyFile = new File(String.valueOf(FichiersBibliotheque.getInstance().getPDFPath(Integer.parseInt(pdfId))));
        FichiersBibliotheque.getInstance().supprimerPDF(Integer.parseInt(pdfId));
        MyFile.delete();

        response.sendRedirect("/membre/liste-pdf");

    }
}
