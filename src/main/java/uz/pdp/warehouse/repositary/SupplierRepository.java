package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.Input;
import uz.pdp.warehouse.entity.tamplet.Supplier;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberNot(String phoneNumber);

    @Query(value = "select * from supplier \n" +
            "join input i on supplier.id = i.supplier_id\n" +
            "join warehouse w on w.id = i.warehouse_id\n" +
            "where warehouse_id =: warId",nativeQuery = true)
    List<Supplier> getAllByNative(Integer warId);


}
