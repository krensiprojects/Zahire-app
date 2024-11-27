package al.polis.zahire.repository;

import al.polis.zahire.model.CatalogueProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueProductRepository extends JpaRepository<CatalogueProduct, Long> {


    //List<CatalogueProduct> findByDescriptionContainsIgnoreCase(String searchCriterion);

    // Search by description, case-insensitive
    @Query("SELECT p FROM CatalogueProduct p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :searchCriterion, '%'))")
    List<CatalogueProduct> findByDescriptionContainsIgnoreCase(@Param("searchCriterion") String searchCriterion);

    // Search by description with pagination and sorting
    @Query("SELECT p FROM CatalogueProduct p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :searchCriterion, '%'))")
    Page<CatalogueProduct> findByDescriptionContainsIgnoreCase(@Param("searchCriterion") String searchCriterion, Pageable pageable);

    // Fetch the whole catalogue
    @Query("SELECT p FROM CatalogueProduct p")
    List<CatalogueProduct> findTheWholeCatalogue();

    // Fetch the whole catalogue with pagination and sorting
    @Query("SELECT p FROM CatalogueProduct p")
    Page<CatalogueProduct> findAll(Pageable pageable);
}
