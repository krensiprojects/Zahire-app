package al.polis.zahire.repository;

import al.polis.zahire.model.UserProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProducerRepository extends JpaRepository<UserProducer, Long> {
}
