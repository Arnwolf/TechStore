package com.techstore.repositories;

import com.techstore.entities.Review;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemReviewRepository implements Repository<Review> {

    @Override
    public void add(final Review entity) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO item_reviews(user_id, item_id, rating, description, creation_date) VALUES(?,?,?,?,?)")) {
            statement.setString(1, entity.getUserID());
            statement.setString(2, entity.getItemID());
            statement.setInt(3, entity.getRating());
            statement.setString(4, entity.getDescription());
            statement.setObject(5, entity.getDatetime());
            statement.execute();
        }
    }

    @Override
    public void update(final Review entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final Review entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Review> query(final SqlSpecification spec) throws SQLException {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(spec.toSql())) {
            query.execute();

            ResultSet result = query.getResultSet();
            List<Review> itemReviews = new ArrayList<>();

            while (result.next()) {
                Review review = new Review();
                review.setDatetime(result.getTimestamp("creation_date").toLocalDateTime());
                review.setUserID(result.getString("user_id"));
                review.setUserName(result.getString("name"));
                review.setDescription(result.getString("description"));
                review.setRating(result.getInt("rating"));
                review.setItemID(result.getString("item_id"));

                itemReviews.add(review);
            }

            return itemReviews;
        }
    }
}
