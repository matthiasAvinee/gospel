package servlets;

import entities.PDF;
import manager.FichiersBibliotheque;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/administrateur/ajout-pdf")
@MultipartConfig
public class ajoutPDFServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part pdfFile = request.getPart("file");

        String nomPDF = null;
        PDF pdf = null;

        try {

            nomPDF = request.getParameter("nom-pdf");

        }catch (NumberFormatException ignored){
        }
        pdf = new PDF(null,nomPDF);

        try{
            PDF createdPDF = FichiersBibliotheque.getInstance().addPDF(pdf,pdfFile);

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            request.getSession().setAttribute("errorMessage", errorMessage);

            response.sendRedirect("/administrateur/ajouter-pdf");
        }

        response.sendRedirect("/membre/liste-pdf");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("ajouterPDF", context, response.getWriter());

    }
}
