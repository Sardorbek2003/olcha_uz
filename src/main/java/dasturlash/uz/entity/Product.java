package dasturlash.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private String images; // yangi rasm ro'yxati maydoni



    public Product(ResultSet resultSet) throws SQLException {
        // ResultSet'dan obyekt yaratilayotganda rasm ro'yxatini JSON formatida qabul qilish kerak
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

    public Product(String name, int price, boolean active, String createdBy, String description, String images) {
        this.name = name;
        this.price = price;
        this.active = active;
        this.createdBy = createdBy;
        this.updatedBy = createdBy;
        this.description = description;
        this.images = images;
    }
}
