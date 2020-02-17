package com.techstore.rest;

import com.techstore.services.CategoriesService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoriesController {
    private CategoriesService categoriesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories() {
        try {
            return Response.status(200).entity(categoriesService.getAllCategories()).build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.toString()).build();
        }
    }

    @GET
    @Path("/{categoryID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("categoryID") String categoryID) {
        try {
            return Response.status(200).entity(categoriesService.getSubCategories(categoryID)).build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc).build();
        }
    }
}