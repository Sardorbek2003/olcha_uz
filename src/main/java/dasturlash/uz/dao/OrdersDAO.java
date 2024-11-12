package dasturlash.uz.dao;

import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.Orders;

import java.sql.*;
import java.util.*;
import java.time.*;


public class OrdersDAO {

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT id, user_id, status, activ, created_date, updated_date FROM orders";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Orders order = new Orders(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("status"),
                        resultSet.getBoolean("activ"),
                        resultSet.getString("created_date"),
                        resultSet.getString("updated_date")
                );
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void addOrder(Orders order) {
        String query = "INSERT INTO orders (user_id, status, activ) VALUES (?, ?, ?)";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setBoolean(3, order.isActiv());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderById(int id) {
        String query = "DELETE FROM orders WHERE id = ?";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Orders order) {
        LocalDateTime now = LocalDateTime.now();

        Timestamp timestampNow = Timestamp.valueOf(now);
        String query = "UPDATE orders SET user_id = ?, status = ?, activ = ?, updated_date = ? WHERE id = ?";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setBoolean(3, order.isActiv());
            preparedStatement.setTimestamp(4, timestampNow);
            preparedStatement.setInt(5, order.getId());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
