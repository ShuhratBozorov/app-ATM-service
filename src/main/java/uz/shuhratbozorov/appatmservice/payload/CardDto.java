package uz.shuhratbozorov.appatmservice.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class CardDto {
    @Length(min = 16)
    private String number;

    @Size(min = 3, max = 4)
    private String cvvCode;

    @NotNull
    private String fullName;

    @NotNull
    private Date validityPeriod;

    @Length(min = 4)
    private String code;

    private Integer cardTypeId;

    private Integer bankId;

    private Integer currencyId;

    private boolean status;

    private Integer roleId;


}
