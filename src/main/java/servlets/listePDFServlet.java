package servlets;

import entities.PDF;
import manager.FichiersBibliotheque;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@WebServlet("/membre/liste-pdf")
public class listePDFServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        List<PDF> listOfPDF = FichiersBibliotheque.getInstance().listPDF();
        context.setVariable("pdfList", listOfPDF);

        List<Path> listOfPathPDF = FichiersBibliotheque.getInstance().listPathPDF();
        context.setVariable("pdfPathsList",listOfPathPDF);

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("pdf", context, response.getWriter());

    }
}
