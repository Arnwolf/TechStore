package com.techstore.controllers;

import com.techstore.dto.CategoryDto;
import com.techstore.services.category.CategoryService;
import com.techstore.services.category.CategoryServiceImpl;
import com.techstore.services.product.ProductService;
import com.techstore.services.product.ProductServiceImpl;
import com.techstore.services.subscription.SubscriptionService;
import com.techstore.services.subscription.SubscriptionServiceImpl;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


public class HomeController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post"))
            subscribe(req.getParameter("email"));
        else
            mainPage();
    }

    private void subscribe(final String email) throws ServletException, IOException {
        if (email != null && !email.isEmpty()) {
            SubscriptionService subscriptionServiceImpl = SubscriptionServiceImpl.getInstance();
            try {
                subscriptionServiceImpl.subscribe(email);
            } catch (final RuntimeException exc) {
                exc.printStackTrace();
            }
        }

        mainPage();
    }

    private void mainPage() throws ServletException, IOException {
        CategoryService categoriesService = CategoryServiceImpl.getInstance();

        try {
            List<CategoryDto> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));

            ProductService productServiceImpl = ProductServiceImpl.getInstance();
            req.setAttribute("products", productServiceImpl.productByCriteria(ProductService.ProductsCriteria.POPULAR));
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
        }

        forward("home");
    }
}
