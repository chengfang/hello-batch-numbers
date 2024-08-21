package sample;

import static sample.StartServlet.jobOperator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RestartServlet", urlPatterns = {"/restart"})
public class RestartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        final String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            throw new ServletException("Missing id parameter in request");
        }

        final Properties properties = new Properties();
//        properties.setProperty("jberet.restart.mode", "strict");
        final long restartId = jobOperator.restart(Long.valueOf(id), properties);
        out.printf("Restarted %s -> %s%n", id, restartId);

        out.flush();
        out.close();
    }

}