package al.polis.zahire.service.impl;

import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.dto.ProductSearchReqDto;
import al.polis.zahire.dto.ProductSearchRespDto;
import al.polis.zahire.dto.RemoveProductDto;
import al.polis.zahire.mapper.CatalogProductMapper;
import al.polis.zahire.model.CatalogueProduct;
import al.polis.zahire.repository.CatalogueProductRepository;
import al.polis.zahire.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogueServiceImpl implements CatalogueService {
    @Autowired
    private CatalogueProductRepository catalogueProductRepository;
    @Autowired
    private CatalogProductMapper catalogProductMapper;

    @Override
    public List<ProductSearchRespDto> searchInCatalogue(ProductSearchReqDto request) {
        // Use the repository to find products by search criterion (code)
        List<CatalogueProduct> products = catalogueProductRepository
                .findByDescriptionContainsIgnoreCase(request.getSearchCriterion());

        // Convert the results to ProductSearchRespDto
        return products.stream()
                .map(product ->
                        new ProductSearchRespDto(product.getId(), product.getCode(), product.getDescription(),
                                product.getShortDescription(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void insertNewProduct(InsertProductDto product) {
        // Convert InsertProductDto to CatalogueProduct
        CatalogueProduct catalogueProduct = catalogProductMapper.fromInsertProductDto(product);

        // Save the product to the repository
        catalogueProductRepository.save(catalogueProduct);
    }

    @Override
    public double sumTwoNumbers(double a, double b) {
        return a+b;
    }

    @Override
    public List<ProductSearchRespDto> removeProduct(RemoveProductDto dto) {
        catalogueProductRepository.deleteById(dto.getId());

        // applies again the search filter and returns results
        ProductSearchReqDto search = new ProductSearchReqDto();
        search.setSearchCriterion(dto.getSearchCriterion());

        return searchInCatalogue(search);
    }
}
