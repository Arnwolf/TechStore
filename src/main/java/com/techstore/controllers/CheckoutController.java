package com.techstore.controllers;


import com.techstore.components.ShoppingCart;
import com.techstore.entities.Order;
import com.techstore.entities.User;
import com.techstore.services.OrdersService;
import com.techstore.services.UsersService;
import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CheckoutController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().toLowerCase().equalsIgnoreCase("post"))
            createOrder();
        else
            showOrderForm();
    }

    private void showOrderForm() throws ServletException, IOException {
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
                showOrderForm();
                return;
            }
        }

        ShoppingCart cart = new ShoppingCart(req.getSession());

        if (cart.isCartEmpty()) {
            resp.sendRedirect("cart");
            return;
        }

        Order newOrder = new Order();
        newOrder.setClientPhoneNumber(req.getParameter("phone"));
        newOrder.setClientName(req.getParameter("name"));
        newOrder.setStreet(req.getParameter("street"));
        newOrder.setCity(req.getParameter("city"));
        newOrder.setEmail(req.getParameter("email"));
        newOrder.setCreationDate(LocalDateTime.now());

        OrdersService ordersService = OrdersService.getInstance();
        boolean status = true;

        try {
            ordersService.createOrder(cart, newOrder);
            req.getSession().setAttribute("bin", null);
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
            status = false;
            req.setAttribute("message", exc.getMessage());
        }

        req.setAttribute("message", status ? "Order was created!" : "Error occurred!");
        forward("successful");
    }
}
