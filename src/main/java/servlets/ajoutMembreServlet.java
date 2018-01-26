package servlets;

import entities.Membre;
import manager.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ajoutermembre")
public class ajoutMembreServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());


        templateEngine.process("ajoutMembre", context, resp.getWriter());

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String pseudo = null;
        String mdp = null;
        String role = null;


        try {
            pseudo = req.getParameter("pseudo");
            mdp = req.getParameter("mdp");
            role = req.getParameter("role");

        } catch (NumberFormatException e) {

        }
        // CREATE FILM
        Membre newMembre = new Membre(null, pseudo, mdp, role);
        try {
            Membre createdMembre = MembreLibrary.getInstance().addMembre(newMembre);

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("gestion");
        }
    }
}
