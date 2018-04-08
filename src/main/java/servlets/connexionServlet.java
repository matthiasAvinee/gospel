package servlets;

import Utilis.MotdePasseUtilis;
import dao.impl.impl.MembreDaoImpl;
import manager.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/connexion")
public class connexionServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, getServletContext());


        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());


        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));
        context.setVariable("error", req.getSession().getAttribute("errorMessage"));
        if(req.getSession().getAttribute("errorMessage") != null) {
            context.setVariable("error", req.getSession().getAttribute("errorMessage"));
            req.getSession().removeAttribute("errorMessage");}
        templateEngine.process("connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MembreDaoImpl membreDao = new MembreDaoImpl();
        MotdePasseUtilis motdePasseUtilis = new MotdePasseUtilis();
        req.getSession().removeAttribute("membreConnecte");
        req.getSession().removeAttribute("adminConnecte");

        String identifiant = req.getParameter("pseudo");
        String motDePasse = req.getParameter("mdp");
        String mdp = motdePasseUtilis.genererMotDePasse(motDePasse);

        Map<String, String> listMembre = MembreLibrary.getInstance().listMembresAutorises();
        Map<String, String> listAdmin = MembreLibrary.getInstance().listAdminAutorises();


        if (listAdmin.containsKey(identifiant)
                && motdePasseUtilis.validerMotDePasse(motDePasse,membreDao.getMotdePasse(identifiant))) {

            req.getSession().setAttribute("adminConnecte", identifiant);
            req.getSession().setAttribute("membreConnecte", identifiant);
            resp.sendRedirect("home");
        }

       else if (listMembre.containsKey(identifiant) && motdePasseUtilis.validerMotDePasse(motDePasse,membreDao.getMotdePasse(identifiant))) {

            req.getSession().setAttribute("membreConnecte", identifiant);
            resp.sendRedirect("home");
        }

        else{

            String errorMessage = "Le compte n'existe pas ou le mot de passe est faux";
            req.getSession().setAttribute("errorMessage", errorMessage);
            resp.sendRedirect("connexion");
        }

    }

}

