package ui.controller.handlers;

import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        if (id != null && !id.isBlank()) {
            getService().deleteAppointment(Integer.parseInt(id));
        }
        response.sendRedirect("Controller?command=Overview");
        return null;
    }
}
