package servlets;


import entities.Membre;
import manager.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/administrateur/modifieracceuil")
public class modifieraccueilServlet extends AbstractGenericServlet  {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));


        String idBalise = req.getParameter("id");


            if (req.getSession().getAttribute("posterError") != null) {
                context.setVariable("errorMessage", req.getSession().getAttribute("posterError"));
                req.getSession().removeAttribute("posterError");
            }



            templateEngine.process("modifierAcceuil", context, resp.getWriter());
        }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String texte = null;
        String titre = null;
        Part img=null;

        String idBalise = req.getParameter("id");


        try {
            texte = req.getParameter("titre");
            titre = req.getParameter("texte");

        } catch (NumberFormatException e) {

        }
        try {
            //Paragraphe paragraphe = MembreLibrary.getInstance().updateParagraphe(idBalise, titre,texte,img );

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("modifieraccueil?id=" + idBalise);
        }
        resp.sendRedirect("/administrateur/gestion");
    }
}

