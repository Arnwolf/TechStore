package com.techstore.controllers;

import com.techstore.components.Encoder;
import com.techstore.entities.User;
import com.techstore.providers.RegistrationProvider;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegistrationController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST"))
            registration();
        else {
            req.setAttribute("error", "");
            forward("registration");
        }
    }

    private void registration() throws ServletException, IOException {
        final String email = req.getParameter("email") == null ? "" : req.getParameter("email");
        final String pass = req.getParameter("psw") == null ? "" : req.getParameter("psw");
        final String userName = req.getParameter("name") == null ? "" : req.getParameter("name");
        final String repeatPass = req.getParameter("psw-repeat") == null ? "" : req.getParameter("psw-repeat");
        final String subscribe = req.getParameter("subscribe") == null ? "off" : req.getParameter("subscribe");

        RegistrationProvider provider = new RegistrationProvider(new Encoder());
        String error = "";

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPass(pass);
        newUser.setSubscribed(subscribe.equals("on"));
        newUser.setName(userName);
        try {
            provider.register(newUser, repeatPass);
        } catch (final RuntimeException exc) {
            error = exc.getMessage();
        }

        if (!error.isEmpty()) {
            req.setAttribute("error", error);
            forward("registration");
        } else
            resp.sendRedirect("/login");
    }
}
