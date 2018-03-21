package servlets;

import entities.Evenement;
import entities.Paragraphe;
import manager.EvenementLibrary;
import manager.ParagrapheLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/evenement")
public class evenementServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, getServletContext());


        Date aujourdhui = new Date();

       // String date=aujourdhui.toString();

        String date="2018-03-21";

        List<Evenement> listEvenementAvant = EvenementLibrary.getInstance().listEvenementAvant(date);
        List<Evenement> listEvenementApres = EvenementLibrary.getInstance().listEvenementApres(date);

        context.setVariable("evenements", listEvenementApres);
        context.setVariable("evenementspasse", listEvenementAvant);
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));

        templateEngine.process("evenement", context, resp.getWriter());

    }
}
