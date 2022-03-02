package uz.pdp.warehouse.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouse.entity.tamplet.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    boolean existsByNameNot(String name);

    List<Category> findAllByParentCategoryIdNull();

}