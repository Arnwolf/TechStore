package com.techstore.controllers;

import com.techstore.entities.Category;
import com.techstore.services.CategoriesService;
import com.techstore.services.ProductService;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ProductsController extends BaseController {
    @Override
    public void process() throws ServletException, IOException {
        Map<String, String> searchParams = new TreeMap<>();
        searchParams.put("categoryID",      req.getParameter("categoryID"));
        searchParams.put("categoryParamId", req.getParameter("categoryParamId")); //category_parameter_id
        searchParams.put("itemParamValue",  req.getParameter("itemParamValue"));
        searchParams.put("search",          req.getParameter("search"));

        ProductService productService = ProductService.getInstance();
        CategoriesService categoriesService = CategoriesService.getInstance();

        try {
            List<Category> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));
            req.setAttribute("products", productService.searchedProducts(searchParams));
        } catch (final RuntimeException exc ) {
            exc.printStackTrace();
        }

        forward("products");
    }
}
