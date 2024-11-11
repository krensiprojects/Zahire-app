package repository;

import model.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCostumerRepository extends JpaRepository<UserCustomer,Long> {
}
