package com.techstore.controllers;

import com.techstore.dto.CreateWishDto;
import com.techstore.dto.UserDto;
import com.techstore.entities.Category;
import com.techstore.services.category.CategoriesService;
import com.techstore.services.user.UserService;
import com.techstore.services.wish.WishService;
import com.techstore.services.wish.WishServiceImpl;
import com.techstore.services.user.UserServiceImpl;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


public class WishesController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        final String url = req.getRequestURI();

        final String wishId = req.getParameter("wishId");
        final String itemId = req.getParameter("ItemID");
        final String hashedUserId = (String)req.getSession().getAttribute("UserID");

        if (url.contains("/add"))
            addWish(hashedUserId, Integer.parseInt(itemId));
        else if (url.contains("/remove"))
            removeWish(Integer.parseInt(wishId));
        else {
            WishService wishService = WishServiceImpl.getInstance();
            CategoriesService categoriesService = CategoriesService.getInstance();
            UserService userService = UserServiceImpl.getInstance();

            final UserDto user = userService.getUserProfile(hashedUserId);

            List<Category> roots = categoriesService.getRootCategories();
            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("items", wishService.userWishes(user.id));

            forward("wishes");
        }
    }

    private void addWish(final String userHashedId, final int productId) throws IOException {
        WishService wishService = WishServiceImpl.getInstance();
        UserDto user = UserServiceImpl.getInstance().getUserProfile(userHashedId);

        CreateWishDto dto = new CreateWishDto();
        dto.productId = productId;
        dto.userId = user.id;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~PRODUCT ID: " + productId);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~USER ID: " + user.id);

        wishService.create(dto);

        resp.sendRedirect("/wishes");
    }

    private void removeWish(final int wishId) throws IOException {
        final WishService wishService = WishServiceImpl.getInstance();

        wishService.delete(wishId);

        resp.sendRedirect("/wishes");
    }
}
