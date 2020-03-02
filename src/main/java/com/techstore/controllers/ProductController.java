package com.techstore.controllers;

import com.techstore.dto.ProductDTO;
import com.techstore.entities.Category;
import com.techstore.entities.Review;
import com.techstore.services.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ProductController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("post"))
            createReview();
        else
            showProduct();
    }

    private void showProduct() throws ServletException, IOException {
        Map<String, String> searchedParams = new TreeMap<>();
        searchedParams.put("ItemID",          req.getParameter("ItemID"));
        searchedParams.put("categoryParamId", req.getParameter("categoryParamId"));
        searchedParams.put("itemParamValue",  req.getParameter("itemParamValue"));

        CategoriesService categoriesService = CategoriesService.getInstance();
        ProductService itemsService = ProductService.getInstance();
        ProductDetailsService productDetailsService = ProductDetailsService.getInstance();

        List<String> errors = req.getAttribute("errors") == null
                ? new ArrayList<>() : (ArrayList<String>)req.getAttribute("errors");

        try {
            final List<Category> roots = categoriesService.getRootCategories();
            ProductDTO product = itemsService.detailedSearchedProduct(searchedParams);

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("detailedProduct", product);
        } catch(final RuntimeException exc) {
            exc.printStackTrace();
            errors.add(exc.getMessage());
        }

        boolean isReviewingGranted = false;
        if (req.getSession().getAttribute("UserID") != null)
            isReviewingGranted = true;

        req.setAttribute("errors", errors);
        req.setAttribute("reviewGranted", isReviewingGranted);
        forward("product");
    }

    private void createReview() throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<String> errors = new ArrayList<>();

        if (session.getAttribute("UserID") == null) {
            errors.add("You need to be authorized!");
            req.setAttribute("errors", errors);
            process();
            return;
        }

        final String itemId = req.getParameter("ItemID") != null ?
                req.getParameter("ItemID") : "";
        final String comment = req.getParameter("review-text") != null ?
                req.getParameter("review-text") : "";
        final String score = req.getParameter("score") != null ?
                req.getParameter("score") : "";

        if (itemId.isEmpty() || comment.isEmpty() || score.isEmpty()) {
            errors.add("Please, fill all fields correctly!");
            req.setAttribute("errors", errors);
            process();
            return;
        }

        ProductService itemsService = ProductService.getInstance();
        UsersService usersService = UsersService.getInstance();

        Review review = new Review(usersService.loadUserByHashedId((String)session.getAttribute("UserID")),
                itemsService.product(Integer.parseInt(itemId)),
                req.getParameter("review-text"),
                Integer.parseInt(req.getParameter("score")),
                LocalDateTime.now());

        try {
            ReviewsService.getInstance().addReview(review);
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
            req.setAttribute("errors", errors);
        }

        showProduct();
    }
}
