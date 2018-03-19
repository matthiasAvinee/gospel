package servlets;


import entities.Membre;
import manager.MembreLibrary;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrateur/supprimermembre")
public class supprimerServlet extends AbstractGenericServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        Membre membre= MembreLibrary.getInstance().getMembre(id);


        if(id==1){

            resp.sendRedirect("/administrateur/gestion");
        }
        else {
            MembreLibrary.getInstance().supprimermembre(id);
            resp.sendRedirect("/administrateur/gestion");
        }

    }

}
