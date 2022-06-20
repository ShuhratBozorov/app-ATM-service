package uz.shuhratbozorov.appatmservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.shuhratbozorov.appatmservice.entity.Card;
import uz.shuhratbozorov.appatmservice.entity.Role;
import uz.shuhratbozorov.appatmservice.entity.User;
import uz.shuhratbozorov.appatmservice.entity.enums.RoleName;
import uz.shuhratbozorov.appatmservice.payload.ApiResponse;
import uz.shuhratbozorov.appatmservice.payload.LoginDto;
import uz.shuhratbozorov.appatmservice.payload.RegisterDto;
import uz.shuhratbozorov.appatmservice.repository.CardRepository;
import uz.shuhratbozorov.appatmservice.repository.RoleRepository;
import uz.shuhratbozorov.appatmservice.repository.UserRepository;
import uz.shuhratbozorov.appatmservice.security.JwtProvider;
import uz.shuhratbozorov.appatmservice.utils.CommonUtils;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MailService mailService;

    @Autowired
    JwtProvider jwtProvider;

    public AuthService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public ApiResponse register(RegisterDto registerDto) {

        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail) {
            return new ApiResponse("Email already exists", false);
        }

        Map<String, Object> securityContextHolder = CommonUtils.getPrincipalAndRoleFromSecurityContextHolder();
        Set<Role> principalUserRoles = (Set<Role>) securityContextHolder.get("principalUserRoles");
        Role role = roleRepository.findById(registerDto.getRoleId()).get();
        Set<Role> roleSet = Collections.singleton(role);
        boolean existsDirectorAuthority = CommonUtils.isExistsAuthority(principalUserRoles, RoleName.ROLE_DIRECTOR) &&
                CommonUtils.isExistsAuthority(roleSet, RoleName.ROLE_STAFF);
        boolean existsStaffAuthority = CommonUtils.isExistsAuthority(principalUserRoles, RoleName.ROLE_STAFF);

        if (!(existsDirectorAuthority || existsStaffAuthority)) {
            return new ApiResponse("You don't have the authority", false);
        }

        User user = new User();
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        user.setRoles(roleSet);

        String emailCode = UUID.randomUUID().toString();
        user.setEmailCode(emailCode);

        String subject = "Confirm Account";

        String text = "Your login: " + user.getEmail() + "\n" +
                "password:" + registerDto.getPassword() + "\n" +
                "Confirm => http://localhost:8080/auth/verifyEmail?emailCode=" + user.getEmailCode() + "&email=" + user.getEmail();
        mailService.sendEmail(user.getEmail(), subject, text);

        userRepository.save(user);
        return new ApiResponse("Successfully registered. A confirmation message has been sent to email to activate the account", true);
    }

    public ApiResponse verifyEmail(String emailCode, String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return new ApiResponse("Account confirmed", true);
        }

        return new ApiResponse("Account already confirmed", false);
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            ));

            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getEmail(), user.getRoles());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException e) {
            return new ApiResponse("Email or password incorrect", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }

    public UserDetails loadCardByCardNumber(String number) {
        return cardRepository.findByNumber(number).orElseThrow(() -> new UsernameNotFoundException(number + " not found"));
    }
}
