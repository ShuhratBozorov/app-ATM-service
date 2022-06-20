package uz.shuhratbozorov.appatmservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.shuhratbozorov.appatmservice.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReplenishAtmHistory extends AbsUUIDEntity {
    @ManyToOne
    private Banknote banknote;

    @Transient
    private Double summa;

    private Integer amount;

    @ManyToOne
    private User staff;

    @ManyToOne
    private Bankomat bankomat;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp date;

    public Double getSumma() {
        Integer value = banknote.getValue();
        summa = Double.valueOf(amount * value);
        return summa;
    }

}
