package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.User;
import uz.pdp.warehouse.entity.tamplet.Warehouse;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

}