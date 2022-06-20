package uz.shuhratbozorov.appatmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.shuhratbozorov.appatmservice.entity.Address;
import uz.shuhratbozorov.appatmservice.entity.Role;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
