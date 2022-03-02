package uz.pdp.warehouse.entity.tamplet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier extends AbstracEntity{
    @Column(nullable = false, unique = true)
    private String phoneNumber;
}
