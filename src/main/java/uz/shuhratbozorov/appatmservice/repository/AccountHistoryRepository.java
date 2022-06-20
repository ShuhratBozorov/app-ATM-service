package uz.shuhratbozorov.appatmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.shuhratbozorov.appatmservice.entity.AccountHistory;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, UUID> {
    List<AccountHistory> findAllByBankomatId(Integer bankomat_id);

    List<AccountHistory> findAllByBankomatIdAndAccountTypeIdAndDateBetween(Integer bankomat_id, Integer accountType_id,
                                                                           Timestamp minDate, Timestamp maxDate);
}
