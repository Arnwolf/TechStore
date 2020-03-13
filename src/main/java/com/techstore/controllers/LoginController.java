package com.techstore.controllers;

import com.techstore.dto.AuthDto;
import com.techstore.services.auth.AuthenticationService;
import com.techstore.services.auth.AuthenticationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
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

        AuthenticationService userService = AuthenticationServiceImpl.getInstance();
        AuthDto dto = new AuthDto();
        dto.email = email;
        dto.password = pass;

        try {
            final String hash = userService.auth(dto);

            HttpSession session = req.getSession();

            session.setAttribute("UserID", hash);
            session.setMaxInactiveInterval(30 * 60 * 60); // 30 hours
        } catch (final RuntimeException exc) {
            req.setAttribute("error", exc.getMessage());
            forward("login");
            return;
        }

        resp.sendRedirect("/");
    }
}
