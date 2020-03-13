package com.techstore.controllers;

import com.techstore.dto.UserDto;
import com.techstore.services.user.UserService;
import com.techstore.services.user.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ProfileController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post"))
            updateProfile();
        else
            getInfoPage();
    }

    private void updateProfile() throws ServletException, IOException {

        UserDto updatedProfile = new UserDto();
        updatedProfile.city = req.getParameter("city");
        updatedProfile.email = req.getParameter("email");
        updatedProfile.name = req.getParameter("name");
        updatedProfile.phoneNumber = req.getParameter("phone");
        updatedProfile.hashedId = (String)req.getSession(false).getAttribute("UserID");
        updatedProfile.subscribe = req.getParameter("subscribe").equals("on");
        updatedProfile.street = req.getParameter("street");
        updatedProfile.pass = req.getParameter("newpass") == null ? "" : req.getParameter("newpass");



        String error = "";
        UserService userService = UserServiceImpl.getInstance();

        try {
            userService.updateProfile(updatedProfile);
        } catch (final RuntimeException exc) {
            error = exc.getMessage();
            exc.printStackTrace();
        }

        req.setAttribute("error", error);
        getInfoPage();
    }

    private void getInfoPage() throws ServletException, IOException {
        HttpSession session = req.getSession();

        String error = "";
        UserService userService = UserServiceImpl.getInstance();
        try {
            UserDto user = userService.getUserProfile((String)session.getAttribute("UserID"));

            req.setAttribute("name", user.name);
            req.setAttribute("email", user.email);
            req.setAttribute("city", user.city);
            req.setAttribute("phone", user.phoneNumber);
            req.setAttribute("street", user.street);
            req.setAttribute("isSubscribed", user.subscribe);
        } catch (final RuntimeException exc) {
            error = exc.getMessage();
        }

        req.setAttribute("error", error);
        forward("profile");
    }
}
