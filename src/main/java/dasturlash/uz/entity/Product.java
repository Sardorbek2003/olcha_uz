package dasturlash.uz.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private String description;
    private boolean active;
    private String createdDate;
    private String updatedDate;

    public Product(String name, int price, boolean active, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.active = active;
    }

    public Product(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");

        this.name = resultSet.getString("name");

        this.price = resultSet.getInt("price");

        this.active = resultSet.getBoolean("active");

        this.description = resultSet.getString("description");

        this.createdDate = resultSet.getTimestamp("created_date").toLocalDateTime().toString();

        this.updatedDate = resultSet.getTimestamp("updated_date").toLocalDateTime().toString();
    }
}
