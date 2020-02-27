package com.techstore.controllers;

import com.techstore.entities.User;
import com.techstore.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProfileController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post"))
            updateProfile();
        else
            getInfoPage();
    }

    private void updateProfile() throws ServletException, IOException {
        User newInfo = new User();
        newInfo.setName(req.getParameter("name"));
        newInfo.setPass(req.getParameter("psw") == null ? "" : req.getParameter("psw"));
        newInfo.setEmail(req.getParameter("email"));
        newInfo.setPhoneNumber(req.getParameter("phone"));
        newInfo.setStreet(req.getParameter("street"));
        newInfo.setCity(req.getParameter("city"));
        newInfo.setHashedID((String)req.getSession(false).getAttribute("UserID"));
        newInfo.setSubscribed(req.getParameter("subscribe").equals("on"));

        List<String> errors = new ArrayList<>();
        UsersService userService = UsersService.getInstance();

        try {
            userService.updateUserInfo(newInfo);
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
            exc.printStackTrace();
        }

        req.setAttribute("errors", errors);
        getInfoPage();
    }

    private void getInfoPage() throws ServletException, IOException {
        HttpSession session = req.getSession();

        List<String> errors = new ArrayList<>();
        UsersService userService = UsersService.getInstance();
        try {
            User user = userService.loadUserByHashedId((String)session.getAttribute("UserID"));

            req.setAttribute("name", user.getName());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("city", user.getCity());
            req.setAttribute("phone", user.getPhoneNumber());
            req.setAttribute("street", user.getStreet());
            req.setAttribute("isSubscribed", user.isSubscribed());
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
        }

        if (req.getAttribute("errors") != null && !((List)req.getAttribute("errors")).isEmpty())
            errors.addAll((ArrayList<String>)req.getAttribute("errors"));

        req.setAttribute("errors", errors);
        forward("profile");
    }
}
