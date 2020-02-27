package com.techstore.controllers;

import com.techstore.components.ShoppingCart;
import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.ProductService;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


public class CartController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        final String url = req.getRequestURI();

        if (url.contains("/add"))
            addItem(Integer.parseInt(req.getParameter("ItemID")), req.getParameter("quantity"));
        else if (url.contains("/remove"))
            removeItem(Integer.parseInt(req.getParameter("ItemID")), req.getParameter("quantity"));
        else
            showCart();
    }

    private void addItem(final Integer itemId, final String quantity) throws IOException {
        ShoppingCart cart = new ShoppingCart(req.getSession());

        cart.addProduct(itemId, Integer.parseInt(quantity));
        cart.save();

        resp.sendRedirect("/cart");
    }

    private void removeItem(final Integer itemId, final String quantity) throws IOException {
        ShoppingCart cart = new ShoppingCart(req.getSession());

        if (quantity == null ||quantity.isEmpty())
            cart.removeProduct(itemId);
        else
            cart.removeProduct(itemId, Integer.parseInt(quantity));

        cart.save();

        resp.sendRedirect("/cart");
    }

    private void showCart() throws ServletException, IOException {
        ShoppingCart cart = new ShoppingCart(req.getSession());

        ProductService productService = ProductService.getInstance();
        CategoriesService categoriesService = CategoriesService.getInstance();

        List<Category> roots = categoriesService.getRootCategories();
        req.setAttribute("categories", roots);
        req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
        req.setAttribute("products", productService.products(cart.getIds()));
        req.setAttribute("cart", cart);

        forward("cart");
    }

}
