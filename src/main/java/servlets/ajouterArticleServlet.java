package servlets;


import entities.Membre;
import entities.Paragraphe;
import manager.ParagrapheLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/administrateur/ajouterarticle")
public class ajouterArticleServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        templateEngine.process("ajoutArticle", context, resp.getWriter());

        if(req.getSession().getAttribute("errorMessage") != null) {
            context.setVariable("error", req.getSession().getAttribute("errorMessage"));
            req.getSession().removeAttribute("errorMessage");}

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer idBalise = null;
        String titre = null;
        String texte = null;
        Part img = null;
        String page = null;
        Integer ordre=null;

        try {
            titre = req.getParameter("titre");
            texte = req.getParameter("article");
            page = req.getParameter("radio");
            //img = req.getPart("image");

        } catch (NumberFormatException e) {

        }

        Paragraphe newParagraphe = new Paragraphe(null, titre, texte , img, page,null);

            try {
                Paragraphe createdParagraphe = ParagrapheLibrary.getInstance().addParagraphe(newParagraphe);
                resp.sendRedirect("/"+createdParagraphe.getPage());
            } catch (IllegalArgumentException e) {
                String errorMessage = e.getMessage();

                req.getSession().setAttribute("errorMessage", errorMessage);

                resp.sendRedirect("ajouterparagraphe");
            }


    }
}
