package com.techstore.repositories;

import com.techstore.entities.User;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    @Override
    public void add(final User entity) throws SQLException {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement createStatement = connection.prepareStatement(
                     "INSERT INTO users(email, password, subscribe, name) VALUES(?,?,?,?)")) {
            createStatement.setString(1, entity.getEmail());
            createStatement.setString(2, entity.getPass());
            createStatement.setBoolean(3, entity.isSubscribed());
            createStatement.setString(4, entity.getName());
            createStatement.execute();
        }
    }

    @Override
    public void update(final User entity) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE users SET ");
        boolean isFirstAppend = false;

        if (entity.getHashedID() != null) {
            query.append(String.format("hashed_id='%s'", entity.getHashedID()));
            isFirstAppend = true;
        }
        if (entity.getPass() != null) {
            query.append(String.format("%spassword='%s'", isFirstAppend ? "," : "", entity.getPass()));
            isFirstAppend = true;
        }
        if (entity.getPhoneNumber() != null) {
            query.append(String.format("%sphone_number='%s'", isFirstAppend ? "," : "", entity.getPhoneNumber()));
            isFirstAppend = true;
        }
        if (entity.getEmail() != null) {
            query.append(String.format("%semail='%s'", isFirstAppend ? "," : "", entity.getEmail()));
            isFirstAppend = true;
        }
        if (entity.getName() != null) {
            query.append(String.format("%sname='%s'", isFirstAppend ? "," : "", entity.getName()));
            isFirstAppend = true;
        }
        if (entity.getStreet() != null) {
            query.append(String.format("%sstreet='%s'", isFirstAppend ? "," : "", entity.getStreet()));
            isFirstAppend = true;
        }
        if (entity.getCity() != null) {
            query.append(String.format("%scity='%s'", isFirstAppend ? "," : "", entity.getCity()));
            isFirstAppend = true;
        }
        if (entity.isSubscribed() != null) {
            query.append(String.format("%ssubscribe=%b", isFirstAppend ? "" : ",", entity.isSubscribed()));
        }

        query.append(String.format(" WHERE id=%s", entity.getID()));

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.execute();
        }
    }

    @Override
    public void remove(final User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> query(final SqlSpecification spec) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(spec.toSql())) {
            statement.execute();

            ResultSet result = statement.getResultSet();

            List<User> users = new ArrayList<>();
            while (result.next()) {
                User user = new User();
                user.setEmail(result.getString("email"));
                user.setPass(result.getString("password"));
                user.setName(result.getString("name"));
                user.setCity(result.getString("city"));
                user.setStreet(result.getString("street"));
                user.setPhoneNumber(result.getString("phone_number"));
                user.setID(result.getString("id"));

                user.setSubscribed(result.getBoolean("subscribe"));

                users.add(user);
            }

            return users;
        }
    }
}
