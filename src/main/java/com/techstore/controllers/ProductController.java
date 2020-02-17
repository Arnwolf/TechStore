package com.techstore.controllers;


import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.DetailedItemsService;

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
            getDetails();
    }

    private void getDetails() throws ServletException, IOException {
        Map<String, String> searchedParams = new TreeMap<>();
        searchedParams.put("ItemID", req.getParameter("ItemID") == null ?
                "" : req.getParameter("ItemID"));
        searchedParams.put("name", req.getParameter("name") == null ?
                "" : req.getParameter("name"));
        searchedParams.put("parameter", req.getParameter("parameter") == null ?
                "" : req.getParameter("parameter"));

        CategoriesService categoriesService = CategoriesService.getInstance();
        DetailedItemsService detailedItemsService = DetailedItemsService.getInstance();

        List<String> errors = req.getAttribute("errors") == null
                ? new ArrayList<>() : (ArrayList<String>)req.getAttribute("errors");

        try {
            List<Category> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("detailedItem", detailedItemsService.getSearchedDetailedItem(searchedParams));
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

        DetailedItemsService detailedItemsService = DetailedItemsService.getInstance();

        try {
            detailedItemsService.addReview(itemId,
                    (String)session.getAttribute("UserID"),
                    Integer.parseInt(req.getParameter("score")),
                    req.getParameter("review-text"),
                    LocalDateTime.now());
        } catch (final RuntimeException exc) {
            errors.add(exc.getMessage());
            req.setAttribute("errors", errors);
        }

        forward("product");
    }
}
