package uz.pdp.warehouse.entity.tamplet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product  extends AbstracEntity{
    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment photo;

    @ManyToOne
    private Measurement measurement;

    private String code;
}
