package uz.shuhratbozorov.appatmservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(
            mappedBy = "bank",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Bankomat> bankomatList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "bank",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<User> staff = new ArrayList<>();
}
