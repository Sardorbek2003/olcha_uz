package dasturlash.uz.dao;

import dasturlash.uz.config.PostgresqlConfig;
import dasturlash.uz.entity.Product;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.*;
import java.util.*;

public class ProductDAO {

    private Connection getConnection() throws SQLException {
        return PostgresqlConfig.getConnection();
    }

    @SneakyThrows
    public void addProduct(Product product)  {
        String sql = "INSERT INTO product (name, price, active, description) VALUES (?, ?, ?, ?::jsonb)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setBoolean(3, product.isActive());
            stmt.setString(4, product.getDescription());
            stmt.executeUpdate();
        }
    }

    @SneakyThrows
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(rs);
                productList.add(product);
            }
        }
        return productList;
    }

    @SneakyThrows
    public void deleteProduct(int productId)  {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    @SneakyThrows
    public void updateProduct(Product product)  {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestampNow = Timestamp.valueOf(now);
        String sql = "UPDATE product SET name = ?, price = ?, active = ?, description = ?::jsonb, updated_date = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setBoolean(3, product.isActive());
            stmt.setString(4, product.getDescription());
            stmt.setTimestamp(5, timestampNow);
            stmt.setInt(6, product.getId());
            stmt.executeUpdate();
        }
    }
}
