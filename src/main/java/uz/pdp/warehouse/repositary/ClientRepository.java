package uz.pdp.warehouse.repositary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberNot(String phoneNumber);

    @Query(value = "select * from client\n" +
            "    join output o on client.id = o.client_id\n" +
            "    join warehouse w on w.id = o.warehouse_id\n" +
            "where warehouse_id =:WarehouseId",nativeQuery = true)
    List<Client> getAllByNative(Integer WarehouseId);
}