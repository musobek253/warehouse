package uz.pdp.warehouse.payload;

import lombok.Data;
import java.util.List;

@Data
public class UsersDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private List<Integer> warehousesId;
}
