package servlets;

import manager.FichiersBibliotheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/membre/picture")
public class PictureServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer photoId = Integer.parseInt(request.getParameter("id"));
        Path picturePath = FichiersBibliotheque.getInstance().getPhotoPicturePath(photoId);

        response.setContentType("image/jpeg");
        Files.copy(picturePath, response.getOutputStream());

    }
}
