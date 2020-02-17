package com.techstore.controllers;

import com.techstore.components.Encoder;
import com.techstore.providers.AuthProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends BaseController {
    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            auth();
        } else {
            req.setAttribute("error", "");
            forward("login");
        }
    }

    protected void auth() throws ServletException, IOException {
        final String email = req.getParameter("email") == null ? "" : req.getParameter("email");
        final String pass = req.getParameter("psw") == null ? "" : req.getParameter("psw");

        AuthProvider authProvider = new AuthProvider(new Encoder());

        String hashedUserID;
        try {
            hashedUserID = authProvider.authUser(email, pass);
        } catch (final RuntimeException exc) {
            req.setAttribute("error", exc.getMessage());
            forward("login");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("UserID", hashedUserID);
        session.setMaxInactiveInterval(30 * 60 * 60); // 30 hours

        resp.sendRedirect("/");
    }
}
