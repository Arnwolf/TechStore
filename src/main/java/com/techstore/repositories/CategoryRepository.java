package com.techstore.repositories;

import com.techstore.entities.Category;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryRepository implements Repository<Category> {

    @Override
    public void add(final Category entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(final Category entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final Category entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Category> query(final SqlSpecification specification) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(specification.toSql())) {
            query.execute();
            ResultSet result = query.getResultSet();

            List<Category> list = new ArrayList<>();
            while (result.next()) {
                Category category = new Category();
                category.setId(result.getString("id"));
                category.setName(result.getString("name"));
                category.setParentCategoryID(result.getString("parent_category_id"));

                list.add(category);
            }

            return list;
        }
    }
}
