package dasturlash.uz.dao;

import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.OrderUser;
import dasturlash.uz.entity.Orders;
import dasturlash.uz.entity.User;

import java.sql.*;
import java.util.*;
import java.time.*;


public class OrdersDAO {

    public List<OrderUser> getAllOrders() {
        List<OrderUser> orders = new ArrayList<>();
        String query = "SELECT id, user_id, status, activ, created_date, updated_date FROM orders";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                User user = getUserById(userId);

                Orders order = new Orders(
                        resultSet.getInt("id"),
                        userId,
                        resultSet.getString("status"),
                        resultSet.getBoolean("activ"),
                        resultSet.getString("created_date"),
                        resultSet.getString("updated_date")
                );
                OrderUser orderUser = new OrderUser(user,order);
                orders.add(orderUser);
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
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT id, name, email FROM users WHERE id = ?";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),resultSet.getString("name"),
                            resultSet.getString("email"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}