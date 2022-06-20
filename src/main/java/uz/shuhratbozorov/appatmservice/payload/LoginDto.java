package uz.shuhratbozorov.appatmservice.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;





}
