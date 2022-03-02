package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}