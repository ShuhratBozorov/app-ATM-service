package uz.shuhratbozorov.appatmservice.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.shuhratbozorov.appatmservice.entity.*;
import uz.shuhratbozorov.appatmservice.entity.enums.RoleName;

import java.util.*;

public class CommonUtils {

    public static boolean checkAuthority(RoleName roleName) {
        Map<String, Object> securityContextHolder = getPrincipalAndRoleFromSecurityContextHolder();
        Set<Role> principalUserRoles = (Set<Role>) securityContextHolder.get("principalUserRoles");
        boolean existsDirectorAuthority = CommonUtils.isExistsAuthority(principalUserRoles, roleName);

        if (!existsDirectorAuthority) {
            return true;
        }
        return false;
    }

    public static Map<String, Object> getPrincipalAndRoleFromSecurityContextHolder() {
        Map<String, Object> map = new HashMap<String, Object>();

        User principalUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        principalUser = (User) authentication.getPrincipal();

        Set<Role> principalUserRoles = principalUser.getRoles();
        map.put("principalUser", principalUser);
        map.put("principalUserRoles", principalUserRoles);
        return map;
    }

    public static boolean isExistsAuthority(Set<Role> roles, RoleName roleName) {
        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }

    public static boolean isExistsAuthority(Set<Role> roles, String roleName) {
        return roles.stream().anyMatch(role -> role.getName().name().contains(roleName));
    }


    public static AccountHistory createAccountHistory(
            Banknote banknote, int amount, Card card, AccountType accountType, Bankomat bankomat, Double commissionPercentage) {
        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setBanknote(banknote);
        accountHistory.setAmount(amount);
        accountHistory.setCard(card);
        accountHistory.setAccountType(accountType);
        accountHistory.setBankomat(bankomat);
        accountHistory.setCommissionPercentage(commissionPercentage);
        return accountHistory;
    }

    public static Calendar getCalendarForNow() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    public static Calendar getCalendarForDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void setTimeToEndOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    public static Integer generateCode() {
        return new Random().nextInt((999999 - 100000) + 1) + 100000;
    }
}
