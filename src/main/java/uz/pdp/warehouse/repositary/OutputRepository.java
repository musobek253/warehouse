package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.Output;
import uz.pdp.warehouse.entity.tamplet.OutputProduct;

import java.util.List;

public interface OutputRepository extends JpaRepository<Output, Integer> {

    List<Output> getAllByWarehouseId(Integer warehouse_id);

    boolean existsByCode(String code);

    @Query(value = "select *from output\n" +
            "join warehouse w on w.id = output.warehouse_id\n" +
            "join users_warehouses uw on w.id = uw.warehouses_id\n" +
            "join users u on u.id = uw.users_id\n" +
            "where users_id =:userId",nativeQuery = true)
    List<Output> getAllByNative(Integer userId);

}