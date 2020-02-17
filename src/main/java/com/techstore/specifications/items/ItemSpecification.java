package com.techstore.specifications.items;

abstract class ItemSpecification {
    final String baseQuery =
            "SELECT i.id, i.manufacturer, i.new, i.availability, i.name, i.price, i.main_photo, c.name AS category, i.discount, i.category_id " +
            "FROM items i " +
            "JOIN categories c ON i.category_id=c.id ";
}
