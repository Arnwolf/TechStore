package com.techstore.controllers;

import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CategoriesController extends BaseController {
    @Override
    public void process() throws ServletException, IOException {
        Map<String, String> searchedParams = new TreeMap<>();
        searchedParams.put("categoryID", req.getParameter("categoryID") == null ?
                "" : req.getParameter("categoryID"));

        CategoriesService categoriesService = CategoriesService.getInstance();
        String error = "";
        try {
            List<Category> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));

            List<Category> searchedCategories = categoriesService.getSearchedCategories(searchedParams);
            if (!searchedCategories.isEmpty())
                req.setAttribute("searchedCategories", searchedCategories);
            else {
                resp.sendRedirect(String.format("/products?categoryID=%s", searchedParams.get("categoryID")));
                return;
            }
        } catch(final RuntimeException exc) {
            error = exc.getMessage();
        }

        req.setAttribute("error", error);
        forward("categories");
    }
}
