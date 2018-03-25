package servlets;

import entities.Album;
import entities.Photo;
import manager.FichiersBibliotheque;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@WebServlet("/administrateur/ajouter-photos")
@MultipartConfig
public class ajouterPhotosServlet extends AbstractGenericServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*Part picture = request.getPart("file");

        Album album = null;
        String albumId = request.getParameter("id");

        try {
            album = FichiersBibliotheque.getInstance().getAlbum(Integer.parseInt(albumId));
        } catch (NumberFormatException ignored) {
        }


        Photo newPhoto = new Photo(null, album);
        try {
            Photo createdPhoto = FichiersBibliotheque.getInstance().addPhoto(newPhoto, picture);
        } catch (IllegalArgumentException e) {
            String errorMessage = e.getMessage();

            request.getSession().setAttribute("errorMessage", errorMessage);

            response.sendRedirect("/administrateur/ajouter-photos?id=" + albumId);
        }


        response.sendRedirect("/membre/listePhotos?id="+albumId);
        */

        Album album = null;
        String albumId = request.getParameter("id");

        try {
            album = FichiersBibliotheque.getInstance().getAlbum(Integer.parseInt(albumId));
        } catch (NumberFormatException ignored) {

        }

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
        } else {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator itr = items.iterator();

            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {
                } else {
                    try {
                        String itemName = item.getName();
                        File savedFile = new File("C:/imagesDevWeb\\"+itemName);
                        item.write(savedFile);

                        Photo newPhoto = new Photo(null, album);
                        Photo createdPhoto = FichiersBibliotheque.getInstance().addPhoto(newPhoto, Paths.get("C:/imagesDevWeb\\" + itemName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        response.sendRedirect("/membre/listePhotos?id="+albumId);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        String albumId = request.getParameter("id");
        Album album = FichiersBibliotheque.getInstance().getAlbum(Integer.parseInt(albumId));
        context.setVariable("album", album);

        context.setVariable("pseudoA", request.getSession().getAttribute("adminConnecte"));
        context.setVariable("pseudoM", request.getSession().getAttribute("membreConnecte"));

        TemplateEngine templateEngine = this.createTemplateEngine(request);
        templateEngine.process("ajouterPhotos", context, response.getWriter());



    }
}
