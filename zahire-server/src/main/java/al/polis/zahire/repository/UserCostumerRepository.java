package al.polis.zahire.repository;

import al.polis.zahire.model.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCostumerRepository extends JpaRepository<UserCustomer,Long> {
}
