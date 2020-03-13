package com.techstore.controllers;


import com.techstore.components.ShoppingCart;
import com.techstore.dto.CreateOrderDto;
import com.techstore.dto.UserDto;
import com.techstore.services.order.OrdersService;
import com.techstore.services.user.UserService;
import com.techstore.services.user.UserServiceImpl;
import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;


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

        if (userId != null) {
            UserService userServiceImpl = UserServiceImpl.getInstance();
            try {
                final UserDto user = userServiceImpl.getUserProfile(userId);

                req.setAttribute("name", user.name);
                req.setAttribute("phone", user.phoneNumber);
                req.setAttribute("street", user.street);
                req.setAttribute("city", user.city);
                req.setAttribute("email", user.email);
            } catch (final RuntimeException exc) {
                exc.printStackTrace();
            }
        }

        req.setAttribute("error", "");
        forward("checkout");
    }


    private void createOrder() throws ServletException, IOException {
        final String[] requiredParams = {"name", "phone", "street", "city", "email"};

        String error = "";
        for (final String param : requiredParams) {
            if (req.getParameter(param) == null || req.getParameter(param).isEmpty()) {
                error = String.format("Please, fill field %s right!", param);
                req.setAttribute("error", error);
                showOrderForm();
                return;
            }
        }

        ShoppingCart cart = new ShoppingCart(req.getSession());

        if (cart.isCartEmpty()) {
            resp.sendRedirect("cart");
            return;
        }

        CreateOrderDto dto = new CreateOrderDto();
        dto.city = req.getParameter("city");
        dto.clientEmail = req.getParameter("email");
        dto.clientName = req.getParameter("name");
        dto.clientPhoneNumber = req.getParameter("phone");
        dto.creationDate = LocalDateTime.now();
        dto.status = OrdersService.OrderStatus.PENDING.status();
        dto.totalAmount = cart.getTotalAmount();
        dto.orderProducts = cart.getCart();

        OrdersService ordersService = OrdersService.getInstance();
        try {
            ordersService.createOrder(dto);
            req.getSession().setAttribute("bin", null);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            error = exc.getMessage();
        }

        req.setAttribute("message", error.isEmpty() ? "Order was created!" : error);
        forward("successful");
    }
}
