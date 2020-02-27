package com.techstore.components;

import com.techstore.entities.Product;
import com.techstore.services.ProductService;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;


public class ShoppingCart {
    private HttpSession session;
    private Map<Integer, Integer> productsQuantity; // <id, quantity>
    private BigDecimal totalAmount;

    public ShoppingCart(@NotNull final HttpSession session) {
        this.session = session;

        productsQuantity = session.getAttribute("bin") != null ?
                (TreeMap<Integer, Integer>)session.getAttribute("bin") : new TreeMap<>();

        totalAmount = new BigDecimal("0.00", new MathContext(2));

        if (!productsQuantity.isEmpty()) {
            List<Product> products = ProductService.getInstance().products(productsQuantity.keySet());

            for (Product product : products) {
                totalAmount = totalAmount
                        .add(product.getPrice().subtract(product.getDiscount()))
                        .multiply(BigDecimal.valueOf(productsQuantity.get(product.getId())));
            }
        }
    }

    public Integer getQuantity(final Integer productId) { return productsQuantity.get(productId); }

    public void addProduct(final Integer productId, final Integer quantity) {
        if (productsQuantity.containsKey(productId)) {
            Integer boughtQuantity = productsQuantity.get(productId);
            boughtQuantity += quantity;
            productsQuantity.put(productId, boughtQuantity);
        } else
            productsQuantity.put(productId, quantity);

        Product addedProduct = ProductService.getInstance().product(productId);

        for (int i = 0; i < quantity; ++i)
            totalAmount = totalAmount.add(addedProduct.getPrice().subtract(addedProduct.getDiscount()));
    }

    public void removeProduct(final Integer productId) {
        final int quantity = productsQuantity.get(productId);
        Product toRemove = ProductService.getInstance().product(productId);

        for (int i = 0; i < quantity; ++i)
            totalAmount = totalAmount.subtract(toRemove.getPrice().subtract(toRemove.getDiscount()));

        productsQuantity.remove(productId);
    }

    public void removeProduct(final Integer productId, final int quantity) {
        Integer productQuantity = productsQuantity.get(productId);
        int reduceQuantity;

        if (quantity > productQuantity)
            reduceQuantity = productQuantity;
        else
            reduceQuantity = quantity;

        Product toRemove = ProductService.getInstance().product(productId);
        for (int i = 0; i < reduceQuantity; ++i)
            totalAmount = totalAmount.subtract(toRemove.getPrice().subtract(toRemove.getDiscount()));

        productsQuantity.put(productId, productQuantity - reduceQuantity);
    }

    public void save() {
        session.setAttribute("bin", productsQuantity);
    }

    public boolean isCartEmpty() {
        return productsQuantity.isEmpty();
    }

    public Collection<Integer> getIds() {
        return productsQuantity.keySet();
    }

    public Map<Integer, Integer> getCart() {
        return productsQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
