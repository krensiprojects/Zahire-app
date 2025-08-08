package al.polis.zahire.service;

import al.polis.zahire.dto.UpdateProductDto;
import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.dto.ProductSearchReqDto;
import al.polis.zahire.dto.ProductSearchRespDto;
import al.polis.zahire.dto.RemoveProductDto;

import java.util.List;

public interface CatalogueService {

    List<ProductSearchRespDto> searchInCatalogue(ProductSearchReqDto searchCriterion);

    void insertNewProduct(InsertProductDto insertProductDto);

    double sumTwoNumbers(double a, double b);

    List<ProductSearchRespDto> removeProduct(RemoveProductDto dto);

    ProductSearchRespDto updateProduct(UpdateProductDto dto);

}
