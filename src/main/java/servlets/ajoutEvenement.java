package servlets;

import entities.Evenement;
import manager.EvenementLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/administrateur/ajouterevenement")
public class ajoutEvenement extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        WebContext context = new WebContext(req, resp, getServletContext());
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));



        if(req.getSession().getAttribute("errorMessage") != null) {
            context.setVariable("error", req.getSession().getAttribute("errorMessage"));
            req.getSession().removeAttribute("errorMessage");}
        templateEngine.process("ajoutEvenement", context, resp.getWriter());

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nom = null;
        Double prix=null;
        String description = null;
        String date=null;
        String  adresse= null;


        try {
            nom = req.getParameter("nom");
            adresse = req.getParameter("adresse");
            description = req.getParameter("description");
            try {
                prix = Double.parseDouble(req.getParameter("prix"));
            }
            catch (NullPointerException e){
                prix=0.0;
            }

            date = req.getParameter("date");


        } catch (NumberFormatException e) {

        }

        Evenement newEvenement = new Evenement(null,prix, nom, adresse,description,date);

        try {
            Evenement createdEvenement = EvenementLibrary.getInstance().addEvenement(newEvenement);
            resp.sendRedirect("/evenement");
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            req.getSession().setAttribute("errorMessage", errorMessage);

            resp.sendRedirect("/ajouterevenement");
        }


    }
}
