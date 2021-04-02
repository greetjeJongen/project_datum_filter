package ui.controller;

import domain.service.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected Service service;

    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException;

    public void setModel (Service contactTracingService) {
        this.service = contactTracingService;
    }

    public Service getService() {
        return service;
    }


}