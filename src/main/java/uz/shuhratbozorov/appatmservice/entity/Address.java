package uz.shuhratbozorov.appatmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.shuhratbozorov.appatmservice.entity.template.AbsIntegerEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends AbsIntegerEntity {
    private String place;

    private String street;

    private String district;
}
