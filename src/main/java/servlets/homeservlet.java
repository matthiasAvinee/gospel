package servlets;

import entities.Paragraphe;
import entities.Photo;
import manager.FichiersBibliotheque;
import manager.ParagrapheLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class homeservlet extends AbstractGenericServlet {



        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            TemplateEngine templateEngine = this.createTemplateEngine(req);
            List<Paragraphe> listOfMembres = ParagrapheLibrary.getInstance().listParagraphesAcceuil();

            WebContext context = new WebContext(req, resp, getServletContext());

            List<Photo> galeriePhoto = FichiersBibliotheque.getInstance().listPhotos(1);
            context.setVariable("galerie", galeriePhoto);

            context.setVariable("paragraphes", listOfMembres);
            context.setVariable("pseudoA", req.getSession().getAttribute("adminConnecte"));
            context.setVariable("pseudoM", req.getSession().getAttribute("membreConnecte"));

            templateEngine.process("home", context, resp.getWriter());

        }
    }


