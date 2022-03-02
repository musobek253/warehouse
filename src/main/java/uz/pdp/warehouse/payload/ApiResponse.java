package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String massage;
    private boolean isSuccess;
    private Object object;

    public ApiResponse(String massage, boolean isSuccess) {
        this.massage = massage;
        this.isSuccess = isSuccess;
    }
}
