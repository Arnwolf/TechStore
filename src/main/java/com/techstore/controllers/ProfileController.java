package com.techstore.controllers;

import com.techstore.entities.User;
import com.techstore.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//GET to /registration TODO: BUG!
//POST to /registration
//GET to /login
//POST to /login
//GET to /
//GET to /profile
//getView somehow modifies user`s subscribe value

public class ProfileController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post")) {
            updateProfile();
        } else
            getView();
    }


    private void updateProfile() throws ServletException, IOException {
        Map<String, String> newUserParams = new TreeMap<>();
        newUserParams.put("name", req.getParameter("name") == null ?
                "" : req.getParameter("name"));
        newUserParams.put("street", req.getParameter("street") == null ?
                "" : req.getParameter("street"));
        newUserParams.put("city", req.getParameter("city") == null ?
                "" : req.getParameter("city"));
        newUserParams.put("country", req.getParameter("country") == null ?
                "" : req.getParameter("country"));
        newUserParams.put("phone", req.getParameter("phone") == null ?
                "" : req.getParameter("phone"));
        newUserParams.put("psw", req.getParameter("psw") == null ?
                "" : req.getParameter("psw"));
        newUserParams.put("email", req.getParameter("email") == null ?
                "" : req.getParameter("email"));
        newUserParams.put("subscribe", req.getParameter("subscribe") == null ?
                "" : req.getParameter("subscribe"));
        newUserParams.put("userHashedId", (String)req.getSession(false).getAttribute("UserID"));

        List<String> errors = new ArrayList<>();
        UsersService userService = UsersService.getInstance();

        try {
            userService.updateUserInfo(newUserParams);
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
            exc.printStackTrace();
        }

        req.setAttribute("errors", errors);
        getView();
    }

    private void getView() throws ServletException, IOException {
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
