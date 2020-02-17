package com.techstore.controllers;

import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.ItemsService;
import com.techstore.services.SubscriptionService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class HomeController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post"))
            subscribe(req.getParameter("email"));
        else
            getView();
    }

    private void subscribe(final String email) throws ServletException, IOException {
        if (email != null && !email.isEmpty()) {
            SubscriptionService subscriptionService = SubscriptionService.getInstance();
            try {
                subscriptionService.subscribe(email);
            } catch (final RuntimeException exc) {
                exc.printStackTrace();
                req.setAttribute("error", exc.getMessage());
            }
        }

        getView();
    }

    private void getView() throws ServletException, IOException {
        CategoriesService categoriesService = CategoriesService.getInstance();

        String error = "";
        try {
            List<Category> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("items", ItemsService.getInstance().getMainPageItems());
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            error = exc.getMessage();
        }

        req.setAttribute("error", error);
        forward("home");
    }
}
