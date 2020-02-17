package com.techstore.rest;

import com.techstore.services.DetailedItemsService;
import com.techstore.services.ItemsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;

@Path("/items")
public class ItemsController {
    private ItemsService itemsService;
    private DetailedItemsService detailedItemsService;


    @GET
    @Path("/{itemID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("itemID") final String itemID) {
        Map<String, String> args = new TreeMap<>();
        args.put("ItemID", itemID);

        try {
            return Response.status(200).entity(detailedItemsService.getSearchedDetailedItem(args)).build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc).build();
        }
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {

        try {
            return Response.status(200).entity(itemsService.getMainPageItems()).build();
        } catch (final RuntimeException exc) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc).build();
        }
    }

    @GET
    @Path("/category/{categoryID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryItems(@PathParam("categoryID") final String categoryID) {

        try {
            return Response.status(200).entity(itemsService.getCategoryItems(categoryID)).build();
        } catch (final RuntimeException exc) {
           exc.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.toString()).build();
        }
    }
}
