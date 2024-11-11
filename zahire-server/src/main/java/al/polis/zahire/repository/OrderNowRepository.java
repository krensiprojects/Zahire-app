package al.polis.zahire.repository;

import al.polis.zahire.model.OrderNow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderNowRepository extends JpaRepository<OrderNow, Long> {
}
