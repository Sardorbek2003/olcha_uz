package dasturlash.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    private int id;
    private int user_id;
    private String status;
    private boolean activ;
    private String created_date;
    private String updated_date;
}
