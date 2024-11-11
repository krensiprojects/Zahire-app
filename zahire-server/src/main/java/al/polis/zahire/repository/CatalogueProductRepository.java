package al.polis.zahire.repository;

import al.polis.zahire.model.CatalogueProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueProductRepository extends JpaRepository<CatalogueProduct, Long> {
}
