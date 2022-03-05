package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.InputProduct;
import uz.pdp.warehouse.entity.tamplet.OutputProduct;
import uz.pdp.warehouse.entity.tamplet.Product;

import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

    List<InputProduct> getAllByInputId(Integer inputId);

    List<InputProduct> getAllByInput_WarehouseId(Integer warehouseId);

}