package dasturlash.uz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    private int id;
    private int user_id;
    private String user_name;
    private String user_email;
    private int product_id;
    private String product_name;
    private int quantity;
    private boolean active;
    private String created_date;
    private String updated_date;
}
