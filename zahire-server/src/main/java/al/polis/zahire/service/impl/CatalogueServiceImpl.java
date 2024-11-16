package al.polis.zahire.service.impl;

import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.dto.ProductSearchReqDto;
import al.polis.zahire.dto.ProductSearchRespDto;
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

    @Override
    public List<ProductSearchRespDto> searchInCatalogue(ProductSearchReqDto request) {
        // Use the repository to find products by search criterion (code)
        List<CatalogueProduct> products = catalogueProductRepository
                .findByDescriptionContainsIgnoreCase(request.getSearchCriterion());

        // Convert the results to ProductSearchRespDto
        return products.stream()
                .map(product -> new ProductSearchRespDto(product.getCode(), product.getDescription(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void insertNewProduct(InsertProductDto product) {
        // Convert InsertProductDto to CatalogueProduct
        CatalogueProduct catalogueProduct = new CatalogueProduct();
        catalogueProduct.setCode(product.getCode());
        catalogueProduct.setDescription(product.getDescription());
        catalogueProduct.setPrice(product.getPrice());
        catalogueProduct.setMinimumQty(product.getMinimumQty());
        catalogueProduct.setPackageSize(product.getPackageSize());
        catalogueProduct.setPackageWeight(product.getPackageWeight());

        // Save the product to the repository
        catalogueProductRepository.save(catalogueProduct);
    }
}
