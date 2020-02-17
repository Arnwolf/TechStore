package com.techstore.controllers;

import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.UserWishesService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class WishesController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        final String url = req.getRequestURI();
        final String itemId = req.getParameter("ItemID") == null ?
                "" : req.getParameter("ItemID");

        final String hashedUserId = (String)req.getSession().getAttribute("UserID");

        if (url.contains("/add")) {
            addWish(hashedUserId, itemId);
        } else if (url.contains("/remove")) {
            removeWish(hashedUserId, itemId);
        } else {
            UserWishesService wishesService = UserWishesService.getInstance();
            CategoriesService categoriesService = CategoriesService.getInstance();

            List<Category> roots = categoriesService.getRootCategories();
            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("items", wishesService.getUserWishes(hashedUserId));

            forward("wishes");
        }
    }

    private void addWish(final String userHashedId, final String itemId) throws IOException {
        final UserWishesService wishesService = UserWishesService.getInstance();
        wishesService.addUserWish(userHashedId, itemId);
        resp.sendRedirect("/wishes");
    }

    private void removeWish(final String userHashedId, final String itemId) throws IOException {
        final UserWishesService wishesService = UserWishesService.getInstance();
        wishesService.removeUserWish(userHashedId, itemId);
        resp.sendRedirect("/wishes");
    }
}
