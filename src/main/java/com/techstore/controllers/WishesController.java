package com.techstore.controllers;

import com.techstore.entities.Category;
import com.techstore.entities.Product;
import com.techstore.entities.User;
import com.techstore.entities.Wish;
import com.techstore.services.CategoriesService;
import com.techstore.services.ProductService;
import com.techstore.services.WishesService;
import com.techstore.services.UsersService;
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
            WishesService wishesService = WishesService.getInstance();
            CategoriesService categoriesService = CategoriesService.getInstance();

            List<Category> roots = categoriesService.getRootCategories();
            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("items", wishesService.getUserWishes(hashedUserId));

            forward("wishes");
        }
    }

    private void addWish(final String userHashedId, final Integer itemId) throws IOException {
        final WishesService wishesService = WishesService.getInstance();
        final User user = UsersService.getInstance().loadUserByHashedId(userHashedId);
        final Product product = ProductService.getInstance().product(itemId);

        Wish wish = new Wish(user, product);
        wishesService.addWish(wish);

        resp.sendRedirect("/wishes");
    }

    private void removeWish(final Integer wishId) throws IOException {
        final WishesService wishesService = WishesService.getInstance();

        Wish wish = new Wish();
        wish.setId(wishId);

        wishesService.removeWish(wish);

        resp.sendRedirect("/wishes");
    }
}
