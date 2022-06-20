package uz.shuhratbozorov.appatmservice.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.shuhratbozorov.appatmservice.entity.Card;
import uz.shuhratbozorov.appatmservice.entity.User;

import java.util.Optional;
import java.util.UUID;

public class SpringSecurityAuditingAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
//            User user = (User) authentication.getPrincipal();
            Object principal = authentication.getPrincipal();
            boolean equals = principal.getClass().equals(User.class);
            if (equals) {
                User user = (User) principal;
                return Optional.of(user.getId());
            } else {
                Card card = (Card) principal;
                return Optional.of(card.getId());
            }
        }
        return Optional.empty();
    }
}
