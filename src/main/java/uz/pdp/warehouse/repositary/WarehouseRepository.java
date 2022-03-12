package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.tamplet.Warehouse;

import java.util.Collection;
import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    boolean existsByName(String name);

    boolean existsByNameNot(String name);
    List<Warehouse> findAllByIdIn(List<Integer> ids);

    boolean existsByIdIn(Collection<Integer> id);

}

