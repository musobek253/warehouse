package uz.pdp.warehouse.repositary;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.User;
import uz.pdp.warehouse.entity.tamplet.Warehouse;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select (count(u) > 0) from users u where u.phoneNumber = ?1")
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByIdAndPhoneNumber(Integer id, String phoneNumber);

    @Query("select (count(u) > 0) from users u where u.phoneNumber = ?1 and u.id <> ?2")
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    @Query(value = "select *\n" +
            "from users join users_warehouses uw on users.id = uw.users_id\n" +
            "join warehouse w on w.id = uw.warehouses_id\n" +
            "where warehouses_id =:warId",nativeQuery = true)
    List<User> getAllByNative(Integer warId);

}

