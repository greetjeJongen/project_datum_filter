package ui.controller;


import domain.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private Service service = new Service();
    private HandlerFactory handlerFactory = new HandlerFactory();

    public Controller() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String destination = "index.jsp";
        if (command != null) {
            RequestHandler handler = handlerFactory.getHandler(command, service);
            destination = handler.handleRequest(request, response);
        }
        if (!response.isCommitted()) {
            request.getRequestDispatcher(destination).forward(request, response);
        }
    }


}

