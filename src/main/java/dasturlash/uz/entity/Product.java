package dasturlash.uz.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    private int id;
    private String name;
    private int price;
    private String description;
    private boolean active;
    private String createdBy;
    private String updatedBy;
    private String createdDate;
    private String updatedDate;

    public Product(String name, int price, boolean active, String createdBy,String description) {
        this.name = name;
        this.price = price;
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.description = description;
        this.active = active;
    }

    public Product(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");

        this.name = resultSet.getString("name");

        this.price = resultSet.getInt("price");

        this.active = resultSet.getBoolean("active");

        this.description = resultSet.getString("description");

        this.createdBy = resultSet.getString("created_by");

        this.updatedBy = resultSet.getString("updated_by");

        this.createdDate = resultSet.getTimestamp("created_date").toLocalDateTime().toString();

        this.updatedDate = resultSet.getTimestamp("updated_date").toLocalDateTime().toString();
    }

    public Product(int id, String name, int price, String description, boolean active, String updatedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.active = active;
        this.updatedBy = updatedBy;
    }
}
