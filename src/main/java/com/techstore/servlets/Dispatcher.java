package com.techstore.servlets;

import com.techstore.controllers.BaseController;
import com.techstore.controllers.UnknownController;
import com.techstore.jdbc.ConnectionPool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

@WebServlet(name = "DispatcherServlet",
        loadOnStartup = 1,
        urlPatterns = "/")
public class Dispatcher extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String path = servletConfig.getServletContext().getRealPath("/WEB-INF/application.properties");
        ConnectionPool.setConfiguration(path);

        super.init(servletConfig);
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.close();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        BaseController controller = getController(request);
        controller.init(getServletContext(), request, response);
        controller.process();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        BaseController controller = getController(request);
        controller.init(getServletContext(), request, response);
        controller.process();
    }

    private BaseController getController(HttpServletRequest request) {
        try {
            StringBuilder controllerName = null;

            if (!request.getRequestURI().equals("/")) {
                controllerName = new StringBuilder(request.getRequestURI());
                controllerName.deleteCharAt(controllerName.indexOf("/"));

                if (controllerName.indexOf("/") > -1)
                controllerName.delete(controllerName.indexOf("/"), controllerName.length());

                final String className = controllerName.substring(0, 1).toUpperCase() + controllerName.substring(1);
                controllerName = new StringBuilder(className);
            }

            Class type = Class.forName(
                    String.format("com.techstore.controllers.%sController", controllerName == null ?
                            "Home" : controllerName));

            MethodType methodType = MethodType.methodType(void.class);
            MethodHandle methodHandle = MethodHandles.publicLookup().findConstructor(type, methodType);

            return (BaseController)methodHandle.invoke();
        } catch (final Throwable exc) {
            exc.printStackTrace();
            return new UnknownController();
        }
    }
}
