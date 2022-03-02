package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouse.entity.tamplet.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}