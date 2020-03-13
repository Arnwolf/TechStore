package com.techstore.rest;

import com.techstore.services.product.ProductService;
import com.techstore.services.product.ProductServiceImpl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/product")
public class ProductsController {
    private ProductService itemsService = ProductServiceImpl.getInstance();

    @GET
    @Path("/{itemID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemID") final String itemID) {

        try {
            return Response.status(Response.Status.OK)
                    .entity(itemsService.findById(Integer.parseInt(itemID)))
                    .build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(exc)
                    .build();
        }
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {

        try {
            return Response.status(Response.Status.OK)
                    .entity(itemsService.productByCriteria(ProductService.ProductsCriteria.POPULAR))
                    .build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(exc)
                    .build();
        }
    }

    @GET
    @Path("/category/{categoryID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryItems(@PathParam("categoryID") final String categoryID) {

        try {
            return Response.status(Response.Status.OK)
                    .entity(itemsService.categoryProducts(Integer.parseInt(categoryID)))
                    .build();
        } catch (final RuntimeException exc) {
           exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(exc.toString())
                    .build();
        }
    }
}
