package uz.shuhratbozorov.appatmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.shuhratbozorov.appatmservice.entity.CommissionSet;

@RepositoryRestResource(path = "commissionSet",collectionResourceRel = "list")
public interface CommissionSetRepository extends JpaRepository<CommissionSet, Integer> {
}
