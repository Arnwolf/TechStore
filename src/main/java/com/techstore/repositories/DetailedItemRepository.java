package com.techstore.repositories;

import com.techstore.entities.DetailedItem;
import com.techstore.entities.Parameter;
import com.techstore.entities.Review;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;
import com.techstore.specifications.parameters.ChangeableParameterSpecificationByItemName;
import com.techstore.specifications.parameters.ParameterSpecificationByItemID;
import com.techstore.specifications.reviews.ReviewSpecificationByItemID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DetailedItemRepository implements Repository<DetailedItem> {

    public DetailedItemRepository() { }

    private List<String> getItemPhotos(final String itemId, final Connection connection) throws SQLException {
            try(PreparedStatement photoQuery = connection.prepareStatement(
                    String.format("SELECT * FROM item_photos WHERE item_id=%s", itemId))) {
                photoQuery.execute();

                ResultSet photosResult = photoQuery.getResultSet();
                List<String> itemPhotos = new LinkedList<>();

                while (photosResult.next()) {
                    itemPhotos.add(photosResult.getString("photo"));
                }

                return itemPhotos;
            }
    }


    private List<Parameter> getItemParameters(final String itemId, final String itemName, Connection connection) throws SQLException  {
        try (PreparedStatement itemParamsQuery =
                     connection.prepareStatement(new ParameterSpecificationByItemID(itemId).toSql())) {
            itemParamsQuery.execute();

            ResultSet paramsResult = itemParamsQuery.getResultSet();
            List<Parameter> itemParams = new LinkedList<>();

            while (paramsResult.next()) {
                Parameter parameter = new Parameter();
                parameter.setId(paramsResult.getString("id"));
                parameter.setCategoryDetailId(paramsResult.getString("category_parameter_id"));
                parameter.setItemDetailName(paramsResult.getString("parameter_name"));
                parameter.setItemDetailSymbol(paramsResult.getString("parameter_symbol"));
                parameter.setItemDetailValue(paramsResult.getString("value"));
                parameter.setItemId(paramsResult.getString("item_id"));
                parameter.setChangeable(paramsResult.getBoolean("changeable"));
                parameter.setSearchable(paramsResult.getBoolean("searchable"));

                itemParams.add(parameter);
            }

            try(PreparedStatement changeableItemParamsQuery =
                        connection.prepareStatement(new ChangeableParameterSpecificationByItemName(itemName).toSql())) {
                changeableItemParamsQuery.execute();
                ResultSet changeableParamsResult = changeableItemParamsQuery.getResultSet();

                while (changeableParamsResult.next()) {
                    Parameter parameter = new Parameter();
                    parameter.setId(changeableParamsResult.getString("id"));
                    parameter.setCategoryDetailId(changeableParamsResult.getString("category_parameter_id"));
                    parameter.setItemDetailName(changeableParamsResult.getString("parameter_name"));
                    parameter.setItemDetailSymbol(changeableParamsResult.getString("parameter_symbol"));
                    parameter.setItemDetailValue(changeableParamsResult.getString("value"));
                    parameter.setItemId(changeableParamsResult.getString("item_id"));
                    parameter.setChangeable(changeableParamsResult.getBoolean("changeable"));
                    parameter.setSearchable(changeableParamsResult.getBoolean("searchable"));

                    itemParams.add(parameter);
                }
                return itemParams;
            }
        }
    }

    private List<Review> getItemReviews(final String itemId, final Connection connection) throws SQLException  {
        try (PreparedStatement reviewQuery =
                     connection.prepareStatement(new ReviewSpecificationByItemID(itemId).toSql())) {
            reviewQuery.execute();

            ResultSet reviewsResult = reviewQuery.getResultSet();
            List<Review> itemReviews = new LinkedList<>();

            while (reviewsResult.next()) {
                Review review = new Review();
                review.setDatetime(reviewsResult.getTimestamp("creation_date").toLocalDateTime());
                review.setUserID(reviewsResult.getString("user_id"));
                review.setUserName(reviewsResult.getString("name"));
                review.setDescription(reviewsResult.getString("description"));
                review.setRating(reviewsResult.getInt("rating"));
                review.setItemID(reviewsResult.getString("item_id"));

                itemReviews.add(review);
            }
            return itemReviews;
        }
    }

    @Override
    public void add(final DetailedItem entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(final DetailedItem entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final DetailedItem entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DetailedItem> query(final SqlSpecification spec) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement query = connection.prepareStatement(spec.toSql())) {
            query.execute();

            ResultSet result = query.getResultSet();
            List<DetailedItem> detailedItems = new LinkedList<>();

            while (result.next()) {
                DetailedItem detailedItem = new DetailedItem();
                detailedItem.setId(result.getString("id"));
                detailedItem.setCategoryId(result.getString("category_id"));
                detailedItem.setAvailability(result.getInt("availability"));
                detailedItem.setManufacturer(result.getString("manufacturer"));
                detailedItem.setName(result.getString("name"));
                detailedItem.setPrice(result.getBigDecimal("price"));
                detailedItem.setMainPhoto(result.getString("main_photo"));
                detailedItem.setCategory(result.getString("category"));
                detailedItem.setDiscount(result.getBigDecimal("discount"));
                detailedItem.setNewItem(result.getBoolean("new"));

                List<String> photos = getItemPhotos(detailedItem.getId(), connection);
                photos.add(detailedItem.getMainPhoto());

                detailedItem.setAllPhotos(photos);
                detailedItem.setItemParameters(getItemParameters(detailedItem.getId(), detailedItem.getName(), connection));
                detailedItem.setItemReviews(getItemReviews(detailedItem.getId(), connection));

                detailedItems.add(detailedItem);
            }

            return detailedItems;
        }
    }
}
