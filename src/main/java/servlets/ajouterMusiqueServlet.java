package servlets;

import entities.Musique;
import manager.FichiersBibliotheque;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/administrateur/ajout-musique")
@MultipartConfig
public class ajouterMusiqueServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part musicFile = request.getPart("file");

        String nomMusique = null;
        Musique musique = null;

        try {

            nomMusique = request.getParameter("nom-musique");

        }catch (NumberFormatException ignored){
        }
        musique = new Musique(null,nomMusique);

        try{
            Musique createdMusique = FichiersBibliotheque.getInstance().addMusique(musique,musicFile);

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            request.getSession().setAttribute("errorMessage", errorMessage);

            response.sendRedirect("/administrateur/ajout-musique");
        }

        response.sendRedirect("/membre/musiques");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("ajouterMusique", context, response.getWriter());

    }
}
