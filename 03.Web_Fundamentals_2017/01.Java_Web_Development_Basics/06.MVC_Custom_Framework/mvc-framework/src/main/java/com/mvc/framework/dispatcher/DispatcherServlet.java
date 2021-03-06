package com.mvc.framework.dispatcher;

import com.mvc.framework.controllerAction.ControllerActionPair;
import com.mvc.framework.handlers.HandlerAction;
import com.mvc.framework.handlers.HandlerActionImpl;
import com.mvc.framework.handlers.HandlerMapping;
import com.mvc.framework.handlers.HandlerMappingImpl;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet implements Dispatcher {

    private HandlerMapping handlerMapping;
    private HandlerAction handlerAction;

    public DispatcherServlet() {
        this.handlerMapping = new HandlerMappingImpl();
        this.handlerAction = new HandlerActionImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (this.isResource(request)) {
            this.sendResourceResponse(request, response);
            return;
        }
        this.handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.handleRequest(request, response);
    }

    @Override
    public ControllerActionPair dispatchRequest(HttpServletRequest request) {
        ControllerActionPair controllerActionPair = null;
        try {
            controllerActionPair = this.handlerMapping.findController(request);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
        return controllerActionPair;
    }

    @Override
    public String dispatchAction(HttpServletRequest request,
                                 HttpServletResponse response,
                                 ControllerActionPair controllerActionPair) {
        String view = null;
        try {
            view = this.handlerAction.executeControllerAction(request, response, controllerActionPair);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException | NamingException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ControllerActionPair controllerActionPair = this.dispatchRequest(request);
        if (controllerActionPair != null) {
            String view = this.dispatchAction(request, response, controllerActionPair);
            try {
                if (view.startsWith("redirect:")) {
                    String redirectPath = view.replace("redirect:", "");
                    response.sendRedirect(redirectPath);
                } else {
                    request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
                }
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResourceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();
        String directory =
                URLDecoder.decode(request.getServletContext().getResource("/").getPath(), "UTF-8");
        File file = new File(directory + uri);

        BufferedReader bf = new BufferedReader(new FileReader(file));

        String line = bf.readLine();
        while (line != null) {
            response.getWriter().print(line);
            line = bf.readLine();
        }
    }

    private boolean isResource(HttpServletRequest request) {
        boolean isResource = false;
        String uri = request.getRequestURI();
        if (uri.contains(".")) {
            isResource = true;
        }
        return isResource;
    }

}
