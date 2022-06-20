package uz.shuhratbozorov.appatmservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.shuhratbozorov.appatmservice.entity.template.AbsIntegerEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bankomat extends AbsIntegerEntity {
    private Double maxMoney;

    private Double minMoney;

    @Transient
    private Double balance = 0.0;

    @ManyToOne(cascade = CascadeType.ALL)
    private CommissionSet commissionSet;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne
    private Bank bank;

    @ManyToMany
    private List<Banknote> banknotes;

    @ManyToMany
    private List<CardType> cardTypes;


    @JsonIgnore
    @OneToMany(mappedBy = "bankomat", cascade = CascadeType.ALL)
    private Set<BankomatSet> bankomatSet;


    public Double getBalance() {
        int sum = bankomatSet.stream().mapToInt(value -> (value.getAmount() * value.getBanknote().getValue())).sum();
        balance = Double.valueOf(sum);
        return balance;
    }
}
