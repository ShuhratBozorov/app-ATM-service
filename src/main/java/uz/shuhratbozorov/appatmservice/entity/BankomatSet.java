package uz.shuhratbozorov.appatmservice.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.shuhratbozorov.appatmservice.entity.template.AbsIntegerEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankomatSet extends AbsIntegerEntity {
    @ManyToOne
    private Banknote banknote;

    @Column(nullable = false)
    private Integer amount = 0;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Bankomat bankomat;

    @Transient
    private Double summa;

    public Double getSumma() {
        summa = Double.valueOf(amount * banknote.getValue());
        return summa;
    }
}
