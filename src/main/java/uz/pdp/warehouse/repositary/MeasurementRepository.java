package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouse.entity.tamplet.Measurement;
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
    boolean existsByNameNot(String name);
}
