package servlets;

import entities.Video;
import manager.FichiersBibliotheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/administrateur/supprimer-video")
public class supprimerVideoServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String videoId = request.getParameter("id");

        Video video = FichiersBibliotheque.getInstance().getVideo(Integer.parseInt(videoId));

        File MyFile = new File(String.valueOf(FichiersBibliotheque.getInstance().getVideoPath(Integer.parseInt(videoId))));
        FichiersBibliotheque.getInstance().supprimerVideo(Integer.parseInt(videoId));
        MyFile.delete();

        response.sendRedirect("/membre/videos");

    }
}
