package repository;

import model.UserProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProducerRepository extends JpaRepository<UserProducer, Long> {
}
