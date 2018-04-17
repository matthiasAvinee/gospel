package servlets;

import entities.Musique;
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

@WebServlet("/membre/musiques")
public class listeMusiquesServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        List<Musique> listOfMusiques = FichiersBibliotheque.getInstance().listMusiques();
        context.setVariable("musiquesList", listOfMusiques);

        List<Path> listOfPathMusiques = FichiersBibliotheque.getInstance().listPathMusiques();
        context.setVariable("musiquesPathsList",listOfPathMusiques);

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("musiques", context, response.getWriter());

    }
}
