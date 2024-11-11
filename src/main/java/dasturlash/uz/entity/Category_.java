package dasturlash.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category_ {
    private int id;
    private String name;
    private int  parentId;
    private boolean activ;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    @SneakyThrows
    public Category_(ResultSet resultSet) {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.parentId = resultSet.getInt("parent_id");
        this.activ = resultSet.getBoolean("activ");
        this.created_date = resultSet.getTimestamp("created_date").toLocalDateTime();
        this.updated_date = resultSet.getTimestamp("updated_date").toLocalDateTime();

    }
}