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

@WebServlet("/reinitialisationDuMdp")
public class modifierMdpServlet extends AbstractGenericServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);

        WebContext context = new WebContext(req, resp, getServletContext());

        context.setVariable("error", req.getSession().getAttribute("errorMessage"));


        int id = Integer.parseInt(req.getParameter("id"));



        if (req.getSession().getAttribute("posterError") != null) {
            context.setVariable("errorMessage", req.getSession().getAttribute("posterError"));
            req.getSession().removeAttribute("posterError");
        }

        context.setVariable("membre", MembreLibrary.getInstance().getMembre(id));

        templateEngine.process("modifierMdp", context, resp.getWriter());

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mdp = null;
        String confirmMdp = null;
        int id = Integer.parseInt(req.getParameter("id"));

        if (id==1) {
            resp.sendRedirect("gestion");
        }
        else
        try {
            mdp = req.getParameter("mdp");
            confirmMdp = req.getParameter("confirmMdp");

        } catch (NumberFormatException e) {

        }

        if (mdp == confirmMdp || mdp.equals(confirmMdp)) {
            try {
                Membre createdMembre = MembreLibrary.getInstance().modifierMdp(id,mdp);

            } catch (IllegalArgumentException e) {
                String errorMessage = e.getMessage();

                req.getSession().setAttribute("errorMessage", errorMessage);

                resp.sendRedirect("reinitialisationDuMdp?id="+id);
            }
            resp.sendRedirect("modifiermembre?id="+id);
        }
        else {
            String errorMessage = "Les deux mots de passe ne sont pas identiques";
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("reinitialisationDuMdp?id="+id);

        }
    }

}
