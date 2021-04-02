package ui.controller;

import domain.service.Service;


public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, Service model) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller.handlers."+ handlerName);
            // Java 10
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!!");
        }

        return handler;
    }

}