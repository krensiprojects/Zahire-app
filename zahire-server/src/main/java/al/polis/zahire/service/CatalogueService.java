package al.polis.zahire.service;

import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.dto.ProductSearchReqDto;
import al.polis.zahire.dto.ProductSearchRespDto;

import java.util.List;

public interface CatalogueService {

    List<ProductSearchRespDto> searchInCatalogue(ProductSearchReqDto searchCriterion);

    void insertNewProduct(InsertProductDto insertProductDto);

    double sumTwoNumbers(double a, double b);
}
