package repository;

import model.CatalogueProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueProductRepository extends JpaRepository<CatalogueProduct, Long> {
}
