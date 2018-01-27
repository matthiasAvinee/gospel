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
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));

        templateEngine.process("ajoutMembre", context, resp.getWriter());

        if(req.getSession().getAttribute("errorMessage") != null) {
            context.setVariable("error", req.getSession().getAttribute("errorMessage"));
            req.getSession().removeAttribute("errorMessage");}

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pseudo = null;
        String mdp = null;
        String role = null;
        String confirmMdp = null;


        try {
            pseudo = req.getParameter("pseudo");
            mdp = req.getParameter("mdp");
            confirmMdp = req.getParameter("confirm-mdp");
            role = req.getParameter("radio");

        } catch (NumberFormatException e) {

        }

        Membre newMembre = new Membre(null, pseudo, mdp, role);

        if (mdp == confirmMdp || mdp.equals(confirmMdp)) {
            try {
                Membre createdMembre = MembreLibrary.getInstance().addMembre(newMembre);

            } catch (IllegalArgumentException e) {
                String errorMessage = e.getMessage();

                req.getSession().setAttribute("errorMessage", errorMessage);

                resp.sendRedirect("ajoutermembre");
            }
            resp.sendRedirect("gestion");
        } else {
            String errorMessage = "Les deux mots de passe ne sont pas identiques";
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("ajoutermembre");
        }
    }

}
