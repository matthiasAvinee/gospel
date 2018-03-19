package servlets;

import entities.Album;
import manager.FichiersBibliotheque;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/membre/albums-photos")
public class listeAlbumsServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        List<Album> listOfAlbums = FichiersBibliotheque.getInstance().listAlbums();
        context.setVariable("membre/albumsList", listOfAlbums);

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("membre/albumsPhotos", context, response.getWriter());

    }
}
