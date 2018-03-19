package servlets;

import entities.Paragraphe;
import manager.ParagrapheLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/evenement")
public class evenementServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);
        List<Paragraphe> listOfMembres = ParagrapheLibrary.getInstance().listParagraphesAcceuil();
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        WebContext context = new WebContext(req, resp, getServletContext());

        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));

        templateEngine.process("evenement", context, resp.getWriter());

    }
}
