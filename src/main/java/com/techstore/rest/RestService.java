package com.techstore.rest;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;


@ApplicationPath(value = "api")
public class RestService extends ResourceConfig {

    public RestService() {
        packages(this.getClass().getPackage().getName());
        register(JacksonJsonProvider.class);
    }
}
