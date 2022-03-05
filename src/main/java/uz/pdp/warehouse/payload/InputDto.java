package uz.pdp.warehouse.payload;

import lombok.Data;
import uz.pdp.warehouse.entity.tamplet.Currency;
import uz.pdp.warehouse.entity.tamplet.Supplier;
import uz.pdp.warehouse.entity.tamplet.Warehouse;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
public class InputDto {

    private Timestamp date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
}
