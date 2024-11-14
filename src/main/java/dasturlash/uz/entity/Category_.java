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
    private String createdBy;
    private String updatedBy;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    @SneakyThrows
    public Category_(ResultSet resultSet) {
        this.id = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.parentId = resultSet.getInt("parent_id");
        this.activ = resultSet.getBoolean("activ");
        this.createdBy = resultSet.getString("created_by");
        this.updatedBy = resultSet.getString("updated_by");
        this.created_date = resultSet.getTimestamp("created_date").toLocalDateTime();
        this.updated_date = resultSet.getTimestamp("updated_date").toLocalDateTime();

    }

    public Category_(int id,String name, int parentId, boolean active, String createBy) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.activ = active;
        this.createdBy = createBy;
        this.updatedBy = createBy;
    }


    public Category_(String name, int parentId, boolean active, String createBy) {
        this.name = name;
        this.parentId = parentId;
        this.activ = active;
        this.createdBy = createBy;
        this.updatedBy = createBy;
    }
}
