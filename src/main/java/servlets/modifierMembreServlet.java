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

@WebServlet("/modifiermembre")
public class modifierMembreServlet extends AbstractGenericServlet {



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());

        context.setVariable("error", req.getSession().getAttribute("errorMessage"));


            int id = Integer.parseInt(req.getParameter("id"));

             if (id == 1) {
                resp.sendRedirect("gestion");
            }
            else {
                 if (req.getSession().getAttribute("posterError") != null) {
                     context.setVariable("errorMessage", req.getSession().getAttribute("posterError"));
                     req.getSession().removeAttribute("posterError");
                 }

                 context.setVariable("membre", MembreLibrary.getInstance().getMembre(id));

                 templateEngine.process("modifierMembre", context, resp.getWriter());
             }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo = null;
        String role = null;

        int id = Integer.parseInt(req.getParameter("id"));


            try {
                pseudo = req.getParameter("pseudo");
                role = req.getParameter("radio");

            } catch (NumberFormatException e) {

            }
            try {
                Membre createdMembre = MembreLibrary.getInstance().updateMembre(id, pseudo, role);

            } catch (IllegalArgumentException e) {
                String errorMessage = e.getMessage();

                req.getSession().setAttribute("errorMessage", errorMessage);

                resp.sendRedirect("modifiermembre?id=" + id);
            }
            resp.sendRedirect("gestion");
        }
    }





