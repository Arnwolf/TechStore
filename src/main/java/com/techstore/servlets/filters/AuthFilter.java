package com.techstore.servlets.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
        urlPatterns = {"/profile/*", "/wishes/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("UserID") == null) {
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }
}
