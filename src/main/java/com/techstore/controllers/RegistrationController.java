package com.techstore.controllers;

import com.techstore.dto.NewUserDto;
import com.techstore.services.user.UserServiceImpl;
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

        String error = "";

        NewUserDto newUser = new NewUserDto();
        newUser.email = email;
        newUser.isSubscribe = subscribe.equals("on");
        newUser.pass = pass;
        newUser.repeatedPass = repeatPass;
        newUser.userName = userName;

        try {
            UserServiceImpl.getInstance().register(newUser);
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
