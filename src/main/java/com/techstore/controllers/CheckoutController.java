package com.techstore.controllers;


import com.techstore.components.ShoppingCart;
import com.techstore.entities.User;
import com.techstore.services.OrdersService;
import com.techstore.services.UsersService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        switch (req.getMethod().toLowerCase()) {
            case "post": {
                createOrder();
                break;
            }
            case "get": {
                getOrderForm();
                break;
            }
        }
    }

    private void getOrderForm() throws ServletException, IOException {
        final String userId = (String)req.getSession().getAttribute("UserID");

        List<String> errors = new ArrayList<>();
        if (userId != null) {
            UsersService usersService = UsersService.getInstance();
            try {
                User user = usersService.loadUserByHashedId(userId);

                req.setAttribute("name", user.getName());
                req.setAttribute("phone", user.getPhoneNumber());
                req.setAttribute("street", user.getStreet());
                req.setAttribute("city", user.getCity());
                req.setAttribute("email", user.getEmail());
            } catch (final RuntimeException exc) {
                errors.add(exc.getMessage());
            }
        }

        req.setAttribute("errors", errors);
        forward("checkout");
    }


    private void createOrder() throws ServletException, IOException {
        final String[] requiredParams = {"name", "phone", "street", "city", "email"};

        List<String> errors = new ArrayList<>();
        for (final String param : requiredParams) {
            if (req.getParameter(param) == null || req.getParameter(param).isEmpty()) {
                errors.add("Please, fill field %s right!");
                req.setAttribute("errors", errors);
                process();
                return;
            }
        }

        ShoppingCart cart = new ShoppingCart(req.getSession());

        if (cart.isCartEmpty()) {
            resp.sendRedirect("bin");
            return;
        }

        User user = new User();
        user.setEmail(req.getParameter("email"));
        user.setStreet(req.getParameter("street"));
        user.setCity(req.getParameter("city"));
        user.setName(req.getParameter("name"));
        user.setPhoneNumber(req.getParameter("phone"));

        OrdersService ordersService = OrdersService.getInstance();

        try {
            ordersService.createOrder(cart, user);
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
            req.setAttribute("errors", errors);
            getOrderForm();
            return;
        }

        req.getSession().setAttribute("bin", null);
        forward("successful");
    }
}
