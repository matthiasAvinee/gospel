package servlets;


import entities.Evenement;
import manager.EvenementLibrary;
import manager.ParagrapheLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/administrateur/modifierevenement")
public class modifierEvenementServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));


        int idBalise = Integer.parseInt(req.getParameter("id"));


        if (req.getSession().getAttribute("posterError") != null) {
            context.setVariable("errorMessage", req.getSession().getAttribute("posterError"));
            req.getSession().removeAttribute("posterError");
        }


        context.setVariable("evenement", EvenementLibrary.getInstance().getEvenement(idBalise));
        templateEngine.process("modifierEvenement", context, resp.getWriter());
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nom = null;
        Integer prix=null;
        String description = null;
        String date=null;
        String  adresse= null;
        Integer idBalise = Integer.parseInt(req.getParameter("id"));

        try {
            nom = req.getParameter("nom");
            adresse = req.getParameter("adresse");
            description = req.getParameter("description");
            prix = Integer.parseInt(req.getParameter("prix"));
            date = req.getParameter("date");



        } catch (NumberFormatException e) {

        }


        try {
            EvenementLibrary.getInstance().updateEvenement(idBalise,prix,nom,adresse,description,date);

        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("modifierevenement?id=" + idBalise);
        }
        resp.sendRedirect("/evenement");






    }
}
