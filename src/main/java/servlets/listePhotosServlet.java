package servlets;

import entities.Album;
import entities.Photo;
import manager.FichiersBibliotheque;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@WebServlet("/membre/listePhotos")
public class listePhotosServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        String albumId = request.getParameter("id");

        Album album = FichiersBibliotheque.getInstance().getAlbum(Integer.parseInt(albumId));
        context.setVariable("album", album);

        List<Photo> listOfPhotos = FichiersBibliotheque.getInstance().listPhotos(Integer.parseInt(albumId));
        context.setVariable("photosList", listOfPhotos);

        /*List<String> listOfChemins = FichiersBibliotheque.getInstance().listChemins(Integer.parseInt(albumId));
        context.setVariable("cheminsList",listOfChemins);*/

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("photos", context, response.getWriter());

    }
}
