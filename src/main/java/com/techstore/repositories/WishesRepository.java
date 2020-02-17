package com.techstore.repositories;

import com.techstore.entities.WishedItem;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WishesRepository implements Repository<WishedItem> {

    @Override
    public void add(final WishedItem entity) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(
                     "INSERT INTO users_wishes(user_id, item_id) VALUES(?, ?)")) {
            query.setInt(1, Integer.parseInt(entity.getUserId()));
            query.setString(2, entity.getId());
            query.execute();
        }
    }

    @Override
    public void update(final WishedItem entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final WishedItem entity) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(
                     "DELETE FROM users_wishes WHERE user_id=? AND item_id=?")) {
            query.setString(1, entity.getUserId());
            query.setString(2, entity.getId());

            query.execute();
        }
    }

    @Override
    public List<WishedItem> query(final SqlSpecification specification) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(specification.toSql())) {
            query.execute();

            ResultSet result = query.getResultSet();
            List<WishedItem> items = new LinkedList<>();

            while (result.next()) {
                WishedItem item = new WishedItem();
                item.setId(result.getString("id"));
                item.setCategoryId(result.getString("category_id"));
                item.setAvailability(result.getInt("availability"));
                item.setManufacturer(result.getString("manufacturer"));
                item.setName(result.getString("name"));
                item.setPrice(result.getBigDecimal("price"));
                item.setMainPhoto(result.getString("main_photo"));
                item.setDiscount(result.getBigDecimal("discount"));

                items.add(item);
            }

            return items;
        }
    }
}
