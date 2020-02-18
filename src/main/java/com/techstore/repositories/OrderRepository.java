package com.techstore.repositories;

import com.techstore.entities.Order;
import com.techstore.entities.OrderedItem;
import com.techstore.jdbc.ConnectionPool;
import com.techstore.specifications.SqlSpecification;
import com.techstore.specifications.orders.OrderItemsSpecificationByMultipleID;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class OrderRepository implements Repository<Order> {

    @Override
    public void add(final Order entity) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            for (String itemId : entity.getOrderItemsIds()) {
                try (PreparedStatement checkAvailability = connection.prepareStatement(
                        "SELECT availability FROM items WHERE id=?")) {
                    checkAvailability.setString(1, itemId);
                    checkAvailability.execute();

                    ResultSet resultSet = checkAvailability.getResultSet();
                    while (resultSet.next()) {
                        if (entity.getItemsQuantity().get(itemId) > resultSet.getInt("availability")) {
                            connection.rollback();
                            return;
                        }
                    }
                } catch (final SQLException exc) {
                    connection.rollback();
                    exc.printStackTrace();
                }
            }

            try (PreparedStatement createOrderStatement = connection.prepareStatement(
                    "INSERT INTO orders(total_amount, city, street, phone_number, client_name, creation_date, status) " +
                            "VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS))
            {
                createOrderStatement.setBigDecimal(1, entity.getTotalAmount());
                createOrderStatement.setString(2, entity.getCity());
                createOrderStatement.setString(3, entity.getStreet());
                createOrderStatement.setString(4, entity.getClientPhoneNumber());
                createOrderStatement.setString(5, entity.getClientName());
                createOrderStatement.setObject(6, entity.getCreationDate());
                createOrderStatement.setString(7, entity.getStatus());
                createOrderStatement.execute();

                ResultSet createdOrderID = createOrderStatement.getGeneratedKeys();
                createdOrderID.next();
                final long OrderID = createdOrderID.getLong(1);

                for (String itemId : entity.getOrderItemsIds()) {
                    try (PreparedStatement addOrderItemsStatement = connection.prepareStatement(
                            "INSERT INTO orders_items(order_id, item_id, quantity) VALUES(?, ?, ?)"))
                    {
                        addOrderItemsStatement.setLong(1, OrderID);
                        addOrderItemsStatement.setString(2, itemId);
                        addOrderItemsStatement.setInt(3, entity.getItemsQuantity().get(itemId));
                        addOrderItemsStatement.execute();
                    } catch(final SQLException exc) {
                        connection.rollback();
                        exc.printStackTrace();
                    }

                    try (PreparedStatement reduceAvailabilityStatement = connection.prepareStatement(
                            "UPDATE items SET availability=availability-? WHERE id=?"))
                    {
                        reduceAvailabilityStatement.setInt(1, entity.getItemsQuantity().get(itemId));
                        reduceAvailabilityStatement.setString(2, itemId);
                        reduceAvailabilityStatement.execute();
                    } catch (final SQLException exc) {
                        connection.rollback();
                        exc.printStackTrace();
                    }
                }
            } catch(final SQLException exc) {
                connection.rollback();
                exc.printStackTrace();
            }

            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void update(final Order entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(final Order entity) {
        throw new UnsupportedOperationException();
    }

    private List<Order> fillOrders(final List<Order> orders, final List<OrderedItem> items) {
        for (Order order : orders) {
            List<OrderedItem> orderItems = items.stream()
                    .filter(orderedItem -> orderedItem.getOrderId().equals(order.getId()))
                    .collect(Collectors.toList());

            order.setItemsQuantity(orderItems.stream()
                    .collect(Collectors.toMap(OrderedItem::getId, OrderedItem::getQuantity)));

            order.setOrderItems(orderItems.stream()
                    .map(OrderedItem::getId)
                    .collect(Collectors.toList()));
        }

        return orders;
    }

    private List<Order> getOrderItems(final Connection connection, final List<Order> orders) throws SQLException {
        try (PreparedStatement itemsQuery = connection.prepareStatement(
                new OrderItemsSpecificationByMultipleID(orders.stream()
                        .map(Order::getId)
                        .collect(Collectors.toList())).toSql())) {
            itemsQuery.execute();

            ResultSet itemsResult = itemsQuery.getResultSet();
            List<OrderedItem> items = new ArrayList<>();

            while (itemsResult.next()) {
                OrderedItem item = new OrderedItem();
                item.setId(itemsResult.getString("id"));
                item.setCategoryId(itemsResult.getString("category_id"));
                item.setAvailability(itemsResult.getInt("availability"));
                item.setManufacturer(itemsResult.getString("manufacturer"));
                item.setName(itemsResult.getString("name"));
                item.setPrice(itemsResult.getBigDecimal("price"));
                item.setMainPhoto(itemsResult.getString("main_photo"));
                item.setCategory(itemsResult.getString("category"));
                item.setDiscount(itemsResult.getBigDecimal("discount"));
                item.setNewItem(itemsResult.getBoolean("new"));
                item.setOrderId(itemsResult.getString("order_id"));
                item.setQuantity(itemsResult.getInt("quantity"));

                items.add(item);
            }

            return fillOrders(orders, items);
        }
    }

    @Override
    public List<Order> query(final SqlSpecification spec) throws SQLException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement orderQuery = connection.prepareStatement(spec.toSql())) {
            orderQuery.execute();

            ResultSet result = orderQuery.getResultSet();
            List<Order> orders = new ArrayList<>();

            while (result.next()) {
                Order order = new Order();
                order.setId(result.getString("id"));
                order.setStatus(result.getString("status"));
                order.setTotalAmount(result.getBigDecimal("total_amount"));
                order.setCity(result.getString("city"));
                order.setStreet(result.getString("street"));
                order.setClientName(result.getString("client_name"));
                order.setClientPhoneNumber(result.getString("phone_number"));
                order.setCreationDate(result.getTimestamp("creation_date").toLocalDateTime());

                orders.add(order);
            }

            return getOrderItems(connection, orders);
        }
    }
}
