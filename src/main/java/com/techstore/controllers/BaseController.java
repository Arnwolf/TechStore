package com.techstore.controllers;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class BaseController {
    protected ServletContext context;
    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    public void init(ServletContext servletContext,
                     HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.req = servletRequest;
        this.resp = servletResponse;
    }

    public abstract void process() throws ServletException, IOException;

    protected void forward(final String target) throws ServletException, IOException {
        context.getRequestDispatcher(String.format("/WEB-INF/pages/%s.jsp", target)).forward(req, resp);
    }
}
