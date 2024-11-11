package al.polis.zahire.repository;

import al.polis.zahire.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderNowRepository extends JpaRepository<OrderRow, Long> {
}
