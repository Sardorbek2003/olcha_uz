package dasturlash.uz.dao;

import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class CartDAO {

    public void createCart(Cart cart) {
        String query = "INSERT INTO cart(user_id, product_id, activ,quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, cart.getUser_id());
            statement.setInt(2, cart.getProduct_id());
            statement.setBoolean(3, cart.isActiv());
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
        try (Connection connection = PostgresqlConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setId(resultSet.getInt("id"));
                cart.setUser_id(resultSet.getInt("user_id"));
                cart.setProduct_id(resultSet.getInt("product_id"));
                cart.setQuantity(resultSet.getInt("quantity"));
                cart.setActiv(resultSet.getBoolean("activ"));
                cart.setCreated_date((resultSet.getTimestamp("created_date")).toString());
                cart.setUpdated_date((resultSet.getTimestamp("updated_date")).toString());
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
            statement.setBoolean(3, cart.isActiv());
            statement.setInt(4, cart.getQuantity());
            statement.setTimestamp(5,timestampNow);


            statement.setInt(6, cart.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
