package com.techstore.controllers;

import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.ItemsService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductsController extends BaseController {
    @Override
    public void process() throws ServletException, IOException {
        Map<String, String> searchParams = new TreeMap<>();
        searchParams.put("categoryID", req.getParameter("categoryID") == null ?
                "" : req.getParameter("categoryID"));

        searchParams.put("ParamID", req.getParameter("ParamID") == null ?
                "" : req.getParameter("ParamID"));

        searchParams.put("ParamValue", req.getParameter("ParamValue") == null ?
                "" : req.getParameter("ParamValue"));

        searchParams.put("search", req.getParameter("search") == null ?
                "" : req.getParameter("search"));

        ItemsService itemsService = ItemsService.getInstance();
        CategoriesService categoriesService = CategoriesService.getInstance();

        try {
            List<Category> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("items", itemsService.getSearchedItems(searchParams));
        } catch (final RuntimeException exc ) {
            exc.printStackTrace();
        }

        forward("products");
    }
}
