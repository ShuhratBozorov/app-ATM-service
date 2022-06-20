package uz.shuhratbozorov.appatmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.shuhratbozorov.appatmservice.entity.AccountType;
import uz.shuhratbozorov.appatmservice.entity.enums.AccountTypeName;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    AccountType findByName(AccountTypeName name);
}
