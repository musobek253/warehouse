package uz.pdp.warehouse.repositary;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.Output;
import uz.pdp.warehouse.entity.tamplet.OutputProduct;
import uz.pdp.warehouse.entity.tamplet.Product;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {

    List<OutputProduct> getAllByOutputId(Integer output_id);
    List<OutputProduct> getAllByOutput_WarehouseId(Integer output_warehouse_id);

}