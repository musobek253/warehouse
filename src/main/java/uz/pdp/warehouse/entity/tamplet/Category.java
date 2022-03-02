package uz.pdp.warehouse.entity.tamplet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstracEntity{
    @ManyToOne
    private Category parentCategoryId;
}
