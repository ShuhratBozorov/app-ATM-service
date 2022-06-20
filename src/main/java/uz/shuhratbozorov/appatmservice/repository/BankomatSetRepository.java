package uz.shuhratbozorov.appatmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import uz.shuhratbozorov.appatmservice.entity.Bankomat;
import uz.shuhratbozorov.appatmservice.entity.BankomatSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BankomatSetRepository extends JpaRepository<BankomatSet, Integer> {
    boolean existsByBanknoteId(Integer banknote_id);

    Set<BankomatSet> findByBankomatId(Integer bankomat_id);

    Optional<BankomatSet> findByBanknoteId(Integer banknote_id);

    List<BankomatSet> findAllByBankomatId(Integer bankomat_id);

    @Modifying
    @Query("UPDATE BankomatSet bs SET bs.amount= :amount where bs.id= :id")
    void changeAmount(@Param("id") Integer id, @Param("amount") Integer amount);
}
