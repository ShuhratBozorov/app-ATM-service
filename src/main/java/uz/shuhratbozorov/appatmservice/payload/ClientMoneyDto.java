package uz.shuhratbozorov.appatmservice.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientMoneyDto {
    @NotNull
    private Double summa;

    @NotNull
    private Integer bankomatId;

    @NotNull
    private Integer accountTypeId;
}
