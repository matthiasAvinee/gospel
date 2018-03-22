package servlets;


import entities.Evenement;
import manager.EvenementLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrateur/supprimerevenement")
public class supprimerEvenementServlet extends AbstractGenericServlet{


        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=UTF-8");
            req.setCharacterEncoding("UTF-8");

            int id = Integer.parseInt(req.getParameter("id"));
            EvenementLibrary.getInstance().supprimerevenement(id);
            resp.sendRedirect("/evenement");
        }

}




