package servlets;

import entities.Photo;
import manager.FichiersBibliotheque;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/administrateur/supprimer-photo")
public class supprimerPhotoServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String photoId = request.getParameter("id");

        Photo photo = FichiersBibliotheque.getInstance().getPhoto(Integer.parseInt(photoId));
        File MyFile = new File(String.valueOf(FichiersBibliotheque.getInstance().getPhotoPicturePath(Integer.parseInt(photoId))));

        FichiersBibliotheque.getInstance().supprimerPhoto(Integer.parseInt(photoId));
        MyFile.delete();

        response.sendRedirect("/home");

    }
}
