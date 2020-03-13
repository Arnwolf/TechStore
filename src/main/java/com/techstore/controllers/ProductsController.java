package com.techstore.controllers;

import com.techstore.dto.CategoryDto;
import com.techstore.services.category.CategoryService;
import com.techstore.services.category.CategoryServiceImpl;
import com.techstore.services.product.ProductService;
import com.techstore.services.product.ProductServiceImpl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


public class ProductsController extends BaseController {
    @Override
    public void process() throws ServletException, IOException {
        CategoryService categoriesService = CategoryServiceImpl.getInstance();

        try {
            List<CategoryDto> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));

            ProductService productService = ProductServiceImpl.getInstance();

            if (req.getParameter("categoryID") != null) {
                final int categoryId = Integer.parseInt(req.getParameter("categoryID"));

                req.setAttribute("products", productService.categoryProducts(categoryId));
            } else if (req.getParameter("categoryParamId") != null && req.getParameter("itemParamValue") != null) {
                final int categoryParamId = Integer.parseInt(req.getParameter("categoryParamId"));
                final String parameterValue = req.getParameter("itemParamValue");

                req.setAttribute("products", productService.similarParameterProducts(categoryParamId, parameterValue));
            } else if (req.getParameter("search") != null) {
                final String searchName = req.getParameter("search");

                req.setAttribute("products", productService.searchProducts(searchName));
            }

        } catch (final RuntimeException exc ) {
            exc.printStackTrace();
        }

        forward("products");
    }
}
