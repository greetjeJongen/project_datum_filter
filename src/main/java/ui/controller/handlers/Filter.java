package ui.controller.handlers;

import domain.model.MyTimeStamp;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class Filter extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String from = request.getParameter("from");
        String until = request.getParameter("until");
        if (request.getParameter("mode").equals("java"))
            request.setAttribute("agenda", getService().getAllAppointmnentsFromUntil(from, until, "java"));
        else
            request.setAttribute("agenda", getService().getAllAppointmnentsFromUntil(from, until, "sql"));
        request.setAttribute("filterInfo", "All Appointments " + (from.isBlank() ? "" : ("from " + from + " ")) + (until.isBlank() ? "" : ("until " + until)));
        return "agenda.jsp";
    }
}
