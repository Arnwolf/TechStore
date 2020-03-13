package com.techstore.controllers;


import com.techstore.dto.CreateReviewDto;
import com.techstore.entities.Category;
import com.techstore.services.category.CategoriesService;
import com.techstore.services.product.ProductService;
import com.techstore.services.product.ProductServiceImpl;
import com.techstore.services.review.ReviewServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
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

    private Map<String, String> collectParameters() {
        Map<String, String> params = new TreeMap<>();
        params.put("ItemID",          req.getParameter("ItemID"));
        params.put("categoryParamId", req.getParameter("categoryParamId"));
        params.put("itemParamValue",  req.getParameter("itemParamValue"));

        return params;
    }

    private void showProduct() throws ServletException, IOException {
        CategoriesService categoriesService = CategoriesService.getInstance();
        try {
            final List<Category> roots = categoriesService.getRootCategories();
            ProductService productService = ProductServiceImpl.getInstance();

            if (req.getParameter("ItemID") != null) {
                final int productId = Integer.parseInt(req.getParameter("ItemID"));

                req.setAttribute("detailedProduct", productService.findById(productId));
            }

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
        } catch(final RuntimeException exc) {
            exc.printStackTrace();
        }

        req.setAttribute("error", "");
        req.setAttribute("reviewGranted", req.getSession().getAttribute("UserID") != null);
        forward("product");
    }

    private void validateReview() {
        if (req.getParameter("ItemID").isEmpty()
                || req.getParameter("review-text").isEmpty()
                || req.getParameter("score").isEmpty())
            throw new RuntimeException("Please, fill all fields correctly!");
    }

    private void createReview() throws ServletException, IOException {
        HttpSession session = req.getSession();
        String error = "";

        if (session.getAttribute("UserID") == null) {
            error = "You need to be authorized!";
            req.setAttribute("error", error);
            showProduct();
            return;
        }

        try {
            validateReview();
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            error = exc.getMessage();
            req.setAttribute("error", error);
            showProduct();
            return;
        }

        final int productId = Integer.parseInt(req.getParameter("ItemID"));
        final String comment = req.getParameter("review-text");
        final int score = Integer.parseInt(req.getParameter("score"));
        final String userHashedId = (String)req.getSession().getAttribute("UserID");

        CreateReviewDto dto = new CreateReviewDto();
        dto.rating = score;
        dto.description = comment;
        dto.creationDate = LocalDateTime.now();
        dto.productId = productId;
        dto.userHahsedId = userHashedId;

        ReviewServiceImpl.getInstance().create(dto);
        req.setAttribute("error", error);
        showProduct();
    }
}
