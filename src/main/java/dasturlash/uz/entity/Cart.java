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
    private int product_id;
    private int quantity;
    private boolean activ;
    private String created_date;
    private String updated_date;
}
