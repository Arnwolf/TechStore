package com.techstore.controllers;

import com.techstore.components.Encoder;
import com.techstore.providers.AuthProvider;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginController extends BaseController {
    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post"))
            auth();
        else {
            req.setAttribute("error", "");
            forward("login");
        }
    }

    private void auth() throws ServletException, IOException {
        final String email = req.getParameter("email") == null ? "" : req.getParameter("email");
        final String pass = req.getParameter("psw") == null ? "" : req.getParameter("psw");

        AuthProvider authProvider = new AuthProvider(new Encoder());
        authProvider.setSession(req.getSession());

        try {
            authProvider.authUser(email, pass);
        } catch (final RuntimeException exc) {
            req.setAttribute("error", exc.getMessage());
            forward("login");
            return;
        }

        resp.sendRedirect("/");
    }
}
