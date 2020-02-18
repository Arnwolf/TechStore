package com.techstore.controllers;

import com.techstore.components.ShoppingCart;
import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.ItemsService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class CartController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        final String url = req.getRequestURI();

        if (url.contains("/add"))
            addItem(req.getParameter("ItemID"), req.getParameter("quantity"));
        else if (url.contains("/remove"))
            removeItem(req.getParameter("ItemID"), req.getParameter("quantity"));
        else
            getView();
    }

    private void addItem(final String itemId, final String quantity) throws IOException {
        ShoppingCart cart = new ShoppingCart(req.getSession());

        cart.addItem(itemId, Integer.parseInt(quantity));
        cart.save();

        resp.sendRedirect("/cart");
    }

    private void removeItem(final String itemId, final String quantity) throws IOException {
        ShoppingCart cart = new ShoppingCart(req.getSession());

        if (quantity == null ||quantity.isEmpty())
            cart.removeItem(itemId);
        else
            cart.removeItem(itemId, Integer.parseInt(quantity));

        cart.save();

        resp.sendRedirect("/cart");
    }

    private void getView() throws ServletException, IOException {
        ShoppingCart cart = new ShoppingCart(req.getSession());

        ItemsService itemsService = ItemsService.getInstance();
        CategoriesService categoriesService = CategoriesService.getInstance();

        List<Category> roots = categoriesService.getRootCategories();
        req.setAttribute("categories", roots);
        req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
        req.setAttribute("items", itemsService.getItems(cart.getIds()));
        req.setAttribute("cart", cart);

        forward("cart");
    }

}
