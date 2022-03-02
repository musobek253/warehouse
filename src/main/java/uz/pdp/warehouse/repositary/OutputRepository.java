package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.Output;
import uz.pdp.warehouse.entity.tamplet.Warehouse;

public interface OutputRepository extends JpaRepository<Output, Integer> {

}