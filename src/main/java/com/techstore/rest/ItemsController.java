package com.techstore.rest;

import com.techstore.services.ProductService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;


@Path("/product")
public class ItemsController {
    private ProductService itemsService = ProductService.getInstance();

    @GET
    @Path("/{itemID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemID") final String itemID) {
        Map<String, String> args = new TreeMap<>();
        args.put("ItemID", itemID);

        try {
            return Response.status(Response.Status.OK).entity(itemsService.searchedProducts(args)).build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc).build();
        }
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {

        try {
            return Response.status(Response.Status.OK).entity(itemsService.popularProducts()).build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc).build();
        }
    }

    @GET
    @Path("/category/{categoryID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryItems(@PathParam("categoryID") final String categoryID) {
        Map<String, String> args = new TreeMap<>();
        args.put("categoryID", categoryID);

        try {
            return Response.status(Response.Status.OK).entity(itemsService.searchedProducts(args)).build();
        } catch (final RuntimeException exc) {
           exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.toString()).build();
        }
    }

    @GET
    @Path("/product/{productID}/full")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFullProduct(@PathParam("productID") final String productID) {
        Map<String, String> args = new TreeMap<>();
        args.put("ItemID", productID);

        try {
            return Response.status(Response.Status.OK).entity(itemsService.detailedSearchedProduct(args)).build();
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.toString()).build();
        }
    }
}
