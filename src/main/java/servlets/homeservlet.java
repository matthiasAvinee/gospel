package servlets;

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

@WebServlet("/home")
public class homeservlet extends AbstractGenericServlet {



        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            TemplateEngine templateEngine = this.createTemplateEngine(req);

            WebContext context = new WebContext(req, resp, getServletContext());


            templateEngine.process("home", context, resp.getWriter());

        }
    }


