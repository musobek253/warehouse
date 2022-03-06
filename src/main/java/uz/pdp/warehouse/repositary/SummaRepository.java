package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.tamplet.Summa;

import java.util.List;
import java.util.Optional;

@Repository
public interface SummaRepository extends JpaRepository<Summa,Integer> {


    Optional<Summa> findByWarehousesIdInAndProductsIdIn(List<Integer> warehouses_id, List<Integer> products_id);

    @Query("select (count(s) > 0) from Summa s inner join s.warehouses warehouses inner join s.products products " +
            "where warehouses.id in ?1 and products.id in ?2")
    boolean existsByWarehousesIdInAndProductsIdIn(List<Integer> warehouses_id, List<Integer> products_id);

}
