package dasturlash.uz.dao;

import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.Cart;

import java.sql.*;
import java.util.*;
import java.time.*;

public class CartDAO {

    public void createCart(Cart cart) {
        String query = "INSERT INTO cart(user_id, product_id, activ,quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, cart.getUser_id());
            statement.setInt(2, cart.getProduct_id());
            statement.setBoolean(3, cart.isActive());
            statement.setInt(4, cart.getQuantity());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCart(int cartId) {
        System.out.println(cartId);
        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM cart WHERE id = ?")) {
            statement.setInt(1, cartId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cart> getAllCarts() {
        List<Cart> cartList = new ArrayList<>();
        String query = "SELECT c.id, u.name as user_name," +
                        " u.email as user_email," +
                        " p.name as product_name," +
                        " c.quantity, c.activ, c.created_date, c.updated_date " +
                        "FROM cart c " +
                        "JOIN users u ON c.user_id = u.id " +
                        "JOIN products p ON c.product_id = p.id";

        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getInt("id"));
                cart.setUser_name(resultSet.getString("user_name"));
                cart.setUser_email(resultSet.getString("user_email"));
                cart.setProduct_name(resultSet.getString("product_name"));
                cart.setQuantity(resultSet.getInt("quantity"));
                cart.setActive(resultSet.getBoolean("activ"));
                cart.setCreated_date(resultSet.getTimestamp("created_date").toString());
                cart.setUpdated_date(resultSet.getTimestamp("updated_date").toString());
                cartList.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartList;
    }

    public void updateCart(Cart cart) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestampNow = Timestamp.valueOf(now);
        String query = "UPDATE cart SET user_id = ?, product_id = ?, activ = ?,quantity = ?, updated_date = ? WHERE id = ?";
        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, cart.getUser_id());
            statement.setInt(2, cart.getProduct_id());
            statement.setBoolean(3, cart.isActive());
            statement.setInt(4, cart.getQuantity());
            statement.setTimestamp(5,timestampNow);


            statement.setInt(6, cart.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
