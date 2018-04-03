package servlets;


import entities.Membre;
import entities.Paragraphe;
import manager.MembreLibrary;
import manager.ParagrapheLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/administrateur/modifierparagraphe")
public class modifieraccueilServlet extends AbstractGenericServlet  {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));


        int idBalise = Integer.parseInt(req.getParameter("id"));


            if (req.getSession().getAttribute("posterError") != null) {
                context.setVariable("errorMessage", req.getSession().getAttribute("posterError"));
                req.getSession().removeAttribute("posterError");
            }


            context.setVariable("paragraphe", ParagrapheLibrary.getInstance().getParagraphe(idBalise));
            templateEngine.process("modifierParagraphe", context, resp.getWriter());
        }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String texte = null;
        String titre = null;
        Part img=null;
        Integer ordre=null;

        Integer idBalise = Integer.parseInt(req.getParameter("id"));
        Paragraphe paragraphe2= ParagrapheLibrary.getInstance().getParagraphe(idBalise);

        try {
            titre = req.getParameter("titre");
            texte = req.getParameter("article");
            ordre= Integer.parseInt(req.getParameter("ordre"));

        } catch (NumberFormatException e) {

        }
        try {
            Paragraphe paragraphe = ParagrapheLibrary.getInstance().updateParagraphe(idBalise,titre,texte,null,ordre);

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("modifierparagraphe?id=" + idBalise);
        }
        resp.sendRedirect("/"+paragraphe2.getPage());
    }
}

