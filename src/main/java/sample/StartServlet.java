package sample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StartServlet", urlPatterns = {"/start"})
public class StartServlet extends HttpServlet {
    final static JobOperator jobOperator = BatchRuntime.getJobOperator();
    final static String job = "numbers";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String jobName = request.getParameter("job");
        if (jobName == null || jobName.isEmpty()) {
            jobName = job;
        }
        final long jobExecutionId = jobOperator.start(jobName, null);
        out.println("Started job execution: " + jobName + " " + jobExecutionId);
        out.flush();
        out.close();
    }

}