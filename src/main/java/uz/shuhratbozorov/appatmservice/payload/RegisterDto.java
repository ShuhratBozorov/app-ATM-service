package uz.shuhratbozorov.appatmservice.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String firstname;

    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Integer roleId;


}
