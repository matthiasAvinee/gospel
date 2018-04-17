package servlets;

import entities.Video;
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

@WebServlet("/administrateur/ajout-video")
@MultipartConfig
public class ajoutVideoServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part videoFile = request.getPart("file");

        String nomVideo = null;
        Video video = null;

        try {

            nomVideo = request.getParameter("nom-video");

        }catch (NumberFormatException ignored){
        }
        video = new Video(null,nomVideo);

        try{
            Video createdVideo = FichiersBibliotheque.getInstance().addVideo(video,videoFile);

        }catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            request.getSession().setAttribute("errorMessage", errorMessage);

            response.sendRedirect("/administrateur/ajout-video");
        }

        response.sendRedirect("/membre/videos");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("ajouterVideo", context, response.getWriter());

    }
}
