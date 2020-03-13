package com.techstore.controllers;

import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogoutController extends BaseController {
    @Override
    public void process() throws IOException {
        HttpSession session = req.getSession(false);

        if (session != null)
            session.invalidate();

        resp.sendRedirect("/");
    }
}
