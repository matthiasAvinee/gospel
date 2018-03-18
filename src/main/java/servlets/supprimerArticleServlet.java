package servlets;

import entities.Membre;
import entities.Paragraphe;
import manager.MembreLibrary;
import manager.ParagrapheLibrary;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/administrateur/supprimerparagraphe")
public class supprimerArticleServlet extends AbstractGenericServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        Paragraphe paragraphe= ParagrapheLibrary.getInstance().getParagraphe(id);



            ParagrapheLibrary.getInstance().supprimerParagraphe(id);
            resp.sendRedirect("/"+paragraphe.getPage());
        }

    }

