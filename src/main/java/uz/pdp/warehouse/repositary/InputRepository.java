package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.Input;

public interface InputRepository extends JpaRepository<Input, Integer> {
}