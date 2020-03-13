package com.techstore.controllers;

import com.techstore.dto.CategoryDto;
import com.techstore.services.category.CategoryService;
import com.techstore.services.category.CategoryServiceImpl;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


public class CategoriesController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        final String categoryId = req.getParameter("categoryID") == null ?
                "" : req.getParameter("categoryID");

        CategoryService categoriesService = CategoryServiceImpl.getInstance();
        try {
            List<CategoryDto> roots = categoriesService.getRootCategories();

            req.setAttribute("categories", roots);
            req.setAttribute("subCategories", categoriesService.getSubCategories(roots));

            List<CategoryDto> searchedCategories = categoriesService.getSubCategories(Integer.parseInt(categoryId));
            if (!searchedCategories.isEmpty())
                req.setAttribute("searchedCategories", searchedCategories);
            else {
                resp.sendRedirect(String.format("/products?categoryID=%s", categoryId));
                return;
            }
        } catch(final RuntimeException exc) {
            exc.printStackTrace();
        }

        forward("categories");
    }
}
