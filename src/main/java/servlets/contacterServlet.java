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

@WebServlet("/contacter")
public class contacterServlet extends AbstractGenericServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = this.createTemplateEngine(req);


        List<Paragraphe> listOfMembres = ParagrapheLibrary.getInstance().listParagraphesContact();
        WebContext context = new WebContext(req, resp, getServletContext());

        context.setVariable("paragraphes", listOfMembres);
        context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));

        templateEngine.process("contacter", context, resp.getWriter());

    }


}
