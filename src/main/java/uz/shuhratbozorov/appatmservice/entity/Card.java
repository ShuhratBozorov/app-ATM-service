package uz.shuhratbozorov.appatmservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.shuhratbozorov.appatmservice.entity.template.AbsUUIDEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Card extends AbsUUIDEntity implements UserDetails {


    @Size(min = 16,max = 16)
    @Column(nullable = false,unique = true)
    private String number;

    @Column(nullable = false,length = 3)
    private String cvvCode;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Date validityPeriod;

    @Size(min = 4,max = 4)
    @Column(nullable = false)
    private String password;

    private Double balance;

    @ManyToOne
    private CardType cardType;

    @ManyToOne
    private Currency currency;

    @ManyToOne
    private Bank bank;

    private boolean status;

    @ManyToOne
    private Role role;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
