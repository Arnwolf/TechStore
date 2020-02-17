package com.techstore.repositories;

import com.techstore.entities.Item;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemRepository implements Repository<Item> {

    @Override
    public void add(final Item entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(final Item entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final Item entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Item> query(final SqlSpecification specification) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(specification.toSql())) {
            query.execute();

            ResultSet result = query.getResultSet();
            List<Item> items = new LinkedList<>();

            while (result.next()) {
                Item item = new Item();
                item.setId(result.getString("id"));
                item.setCategoryId(result.getString("category_id"));
                item.setAvailability(result.getInt("availability"));
                item.setManufacturer(result.getString("manufacturer"));
                item.setName(result.getString("name"));
                item.setPrice(result.getBigDecimal("price"));
                item.setMainPhoto(result.getString("main_photo"));
                item.setCategory(result.getString("category"));
                item.setDiscount(result.getBigDecimal("discount"));
                item.setNewItem(result.getBoolean("new"));

                items.add(item);
            }
            return items;
        }
    }
}
