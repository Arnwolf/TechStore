package com.techstore.components;

import com.techstore.dto.PreviewProductDto;
import com.techstore.services.product.ProductServiceImpl;
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
            List<PreviewProductDto> products = ProductServiceImpl.getInstance().findByIds(productsQuantity.keySet());

            for (PreviewProductDto product : products) {
                totalAmount = totalAmount
                        .add(product.price.subtract(product.discount))
                        .multiply(BigDecimal.valueOf(productsQuantity.get(product.id)));
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

        PreviewProductDto addedProduct = ProductServiceImpl.getInstance().findById(productId).product;

        for (int i = 0; i < quantity; ++i)
            totalAmount = totalAmount.add(addedProduct.price.subtract(addedProduct.discount));
    }

    public void removeProduct(final Integer productId) {
        final int quantity = productsQuantity.get(productId);
        PreviewProductDto toRemove = ProductServiceImpl.getInstance().findById(productId).product;

        for (int i = 0; i < quantity; ++i)
            totalAmount = totalAmount.subtract(toRemove.price.subtract(toRemove.discount));

        productsQuantity.remove(productId);
    }

    public void removeProduct(final Integer productId, final int quantity) {
        Integer productQuantity = productsQuantity.get(productId);
        int reduceQuantity;

        if (quantity > productQuantity)
            reduceQuantity = productQuantity;
        else
            reduceQuantity = quantity;

        PreviewProductDto toRemove = ProductServiceImpl.getInstance().findById(productId).product;
        for (int i = 0; i < reduceQuantity; ++i)
            totalAmount = totalAmount.subtract(toRemove.price.subtract(toRemove.discount));

        productsQuantity.put(productId, productQuantity - reduceQuantity);
    }

    public void save() { session.setAttribute("bin", productsQuantity); }

    public boolean isCartEmpty() { return productsQuantity.isEmpty(); }

    public Map<Integer, Integer> getCart() { return productsQuantity; }

    public BigDecimal getTotalAmount() { return totalAmount; }
}
