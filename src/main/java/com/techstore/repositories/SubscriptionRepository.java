package com.techstore.repositories;

import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SubscriptionRepository implements Repository<String> {
    @Override
    public void add(final String email) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(
                     "INSERT INTO subscription(email) VALUES(?)")) {
            query.setString(1, email);
            query.execute();
        }
    }

    @Override
    public void update(final String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final String email) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(
                     "DELETE FROM subscription WHERE email=?")) {
            query.setString(1, email);
            query.execute();
        }
    }

    @Override
    public List<String> query(final SqlSpecification spec) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(spec.toSql())) {
            query.execute();

            ResultSet result = query.getResultSet();
            List<String> subscriptions = new LinkedList<>();

            while (result.next()) {
                final String subscription = result.getString("email");
                subscriptions.add(subscription);
            }

            return subscriptions;
        }
    }
}
