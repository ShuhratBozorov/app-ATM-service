package uz.shuhratbozorov.appatmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shuhratbozorov.appatmservice.entity.template.AbsIntegerEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommissionSet extends AbsIntegerEntity {

    private Double commissionToWithdraw;
    private Double commissionToReplenish;
    private Double commissionToWithdrawForOtherBank;
    private Double commissionToReplenishForOtherBank;

}
