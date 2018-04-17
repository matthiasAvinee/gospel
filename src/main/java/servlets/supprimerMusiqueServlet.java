package servlets;

import entities.Musique;
import manager.FichiersBibliotheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/administrateur/supprimer-musique")
public class supprimerMusiqueServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String musiqueId = request.getParameter("id");

        Musique musique = FichiersBibliotheque.getInstance().getMusique(Integer.parseInt(musiqueId));

        File MyFile = new File(String.valueOf(FichiersBibliotheque.getInstance().getMusiquePath(Integer.parseInt(musiqueId))));
        FichiersBibliotheque.getInstance().supprimerMusique(Integer.parseInt(musiqueId));
        MyFile.delete();

        response.sendRedirect("/membre/musiques");

    }
}
