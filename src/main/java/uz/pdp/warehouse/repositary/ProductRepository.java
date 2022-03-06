package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.Category;
import uz.pdp.warehouse.entity.tamplet.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);
    List<Product> getAllByCategoryId(Integer category_id);

    boolean existsByCode(String code);
    @Query("select (count(p) > 0) from Product p where p.name <> ?1")
    boolean existsByNameNot(String name);
    List<Product> getByIdIn(Collection<Integer> id);

}