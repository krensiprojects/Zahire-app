package repository;

import model.OrderNow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderNowRepository extends JpaRepository<OrderNow, Long> {
}
