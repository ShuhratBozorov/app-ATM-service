package uz.shuhratbozorov.appatmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.shuhratbozorov.appatmservice.entity.Role;
import uz.shuhratbozorov.appatmservice.entity.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleName name);
}
