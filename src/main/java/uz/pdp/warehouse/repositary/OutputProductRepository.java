package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.OutputProduct;
import uz.pdp.warehouse.entity.tamplet.Product;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {

}