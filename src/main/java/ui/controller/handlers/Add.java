package ui.controller.handlers;

import domain.db.DbException;
import domain.model.Appointment;
import domain.model.DomainException;
import domain.model.MyTimeStamp;
import ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Add extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = new ArrayList<>();
        Appointment appointment = new Appointment();
        setName(request, appointment, errors);
        setMyDate(request, appointment, errors);
        if (errors.size() == 0) {
            try {
                service.addAppointment(appointment);
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            return "addForm.jsp";
        }
        response.sendRedirect("Controller?command=Overview");
        return null;
    }

    private void setName(HttpServletRequest request, Appointment appointment, List<String> errors) {
        String name = request.getParameter("name");
        request.setAttribute("previousName", name);
        appointment.setName(name);
    }

    private void setMyDate(HttpServletRequest request, Appointment appointment, List<String> errors) {
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        try {
            MyTimeStamp.checkValidTime(time);
            request.setAttribute("previousDate", date);
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
        try {
            MyTimeStamp.chekValidDate(date);
            request.setAttribute("previousTime", time);
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }
        if (errors.size() == 0)
            appointment.setDate(MyTimeStamp.getTimestampFromDateAndTimeAsString(date, time));
    }


}
