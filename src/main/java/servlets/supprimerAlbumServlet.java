package servlets;

import entities.Photo;
import manager.FichiersBibliotheque;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/administrateur/supprimer-album")
public class supprimerAlbumServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String albumId = request.getParameter("id");

        List<Photo> listPhotosASupprimer = FichiersBibliotheque.getInstance().listPhotos(Integer.parseInt(albumId));

        if (Integer.parseInt(albumId)==1){
            response.sendRedirect("/membre/albums-photos");
        }else {

            if (listPhotosASupprimer.size()!=0){
                for (int i=0; i<listPhotosASupprimer.size();i++){
                    File MyFile = new File(String.valueOf(FichiersBibliotheque.getInstance().getPhotoPicturePath(listPhotosASupprimer.get(i).getIdPhoto())));
                    FichiersBibliotheque.getInstance().supprimerPhoto(listPhotosASupprimer.get(i).getIdPhoto());
                    MyFile.delete();
                }
            }

            FichiersBibliotheque.getInstance().supprimerAlbum(Integer.parseInt(albumId));

            response.sendRedirect("/membre/albums-photos");
        }
    }
}
