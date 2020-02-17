package com.techstore.controllers;

import javax.servlet.ServletException;
import java.io.IOException;

public class UnknownController extends BaseController {

    @Override
    public void process() throws ServletException, IOException {
        forward("not-found");
    }
}
