package sample;

import static sample.StartServlet.job;
import static sample.StartServlet.jobOperator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StopServlet", urlPatterns = {"/stop"})
public class StopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        final String idToStop = request.getParameter("id");
        if (idToStop != null && !idToStop.isEmpty()) {
            jobOperator.stop(Long.valueOf(idToStop));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            out.println(idToStop + ": " + jobOperator.getJobExecution(Long.valueOf(idToStop)).getBatchStatus());
        } else {
            String jobName = request.getParameter("job");
            if (jobName == null || jobName.isEmpty()) {
                jobName = job;
            }

            final List<Long> runningExecutions = jobOperator.getRunningExecutions(jobName);
            out.println("Running executions of " + jobName + ": " + runningExecutions);
            for (Long id : runningExecutions) {
                jobOperator.stop(id);
            }

            for (Long id : runningExecutions) {
                out.println(id + ": " + jobOperator.getJobExecution(id).getBatchStatus());
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }

            for (Long id : runningExecutions) {
                out.println(id + ": " + jobOperator.getJobExecution(id).getBatchStatus());
            }
        }

        out.flush();
        out.close();
    }

}