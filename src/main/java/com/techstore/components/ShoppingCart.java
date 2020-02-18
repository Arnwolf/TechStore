package com.techstore.components;

import com.techstore.entities.Item;
import com.techstore.services.ItemsService;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class ShoppingCart {
    private HttpSession session;
    private Map<String, Integer> itemsQuantity; // <id, quantity>
    private BigDecimal totalAmount;

    public ShoppingCart(@NotNull final HttpSession session) {
        this.session = session;

        itemsQuantity = session.getAttribute("bin") != null ?
                (TreeMap<String, Integer>)session.getAttribute("bin") : new TreeMap<>();

        totalAmount = new BigDecimal("0.00", new MathContext(2));

        List<Item> items = ItemsService.getInstance().getItems(itemsQuantity.keySet());

        for (Item item : items) {
            totalAmount = totalAmount
                    .add(item.getPrice().subtract(item.getDiscount()))
                    .multiply(BigDecimal.valueOf(itemsQuantity.get(item.getId())));
        }
    }

    public Integer getQuantity(final String itemId) {
        return itemsQuantity.get(itemId);
    }

    public void addItem(final String itemId, final Integer quantity) {
        if (itemsQuantity.containsKey(itemId)) {
            Integer boughtQuantity = itemsQuantity.get(itemId);
            boughtQuantity += quantity;
            itemsQuantity.put(itemId, boughtQuantity);
        } else
            itemsQuantity.put(itemId, quantity);

        Item addedItem = ItemsService.getInstance().getItem(itemId);

        for (int i = 0; i < quantity; ++i)
            totalAmount = totalAmount.add(addedItem.getPrice().subtract(addedItem.getDiscount()));
    }

    public void removeItem(final String itemId) {
        final int quantity = itemsQuantity.get(itemId);
        Item toRemove = ItemsService.getInstance().getItem(itemId);

        for (int i = 0; i < quantity; ++i)
            totalAmount = totalAmount.subtract(toRemove.getPrice().subtract(toRemove.getDiscount()));

        itemsQuantity.remove(itemId);
    }

    public void removeItem(final String itemId, final int quantity) {
        Integer cartItemQuantity = itemsQuantity.get(itemId);
        int reduceQuantity;

        if (quantity > cartItemQuantity)
            reduceQuantity = cartItemQuantity;
        else
            reduceQuantity = quantity;

        Item toRemove = ItemsService.getInstance().getItem(itemId);
        for (int i = 0; i < reduceQuantity; ++i)
            totalAmount = totalAmount.subtract(toRemove.getPrice().subtract(toRemove.getDiscount()));

        itemsQuantity.put(itemId, cartItemQuantity - reduceQuantity);
    }

    public void save() {
        session.setAttribute("bin", itemsQuantity);
    }

    public boolean isCartEmpty() {
        return itemsQuantity.isEmpty();
    }

    public Collection<String> getIds() {
        return itemsQuantity.keySet();
    }

    public Map<String, Integer> getCart() {
        return itemsQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
