package al.polis.zahire.service.impl;

import al.polis.zahire.dto.*;
import al.polis.zahire.mapper.CatalogProductMapper;
import al.polis.zahire.model.CatalogueProduct;
import al.polis.zahire.repository.CatalogueProductRepository;
import al.polis.zahire.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public ProductSearchRespDto updateProduct(UpdateProductDto dto) {
        CatalogueProduct p = catalogueProductRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + dto.getProductId()));

        // Editable fields
        p.setCode(dto.getCode());
        p.setDescription(dto.getDescription());
        p.setShortDescription(dto.getShortDescription());
        p.setPrice(dto.getPrice());
        p.setMinimumQty(dto.getMinimumQty());
        p.setPackageSize(dto.getPackageSize());
        p.setPackageWeight(dto.getPackageWeight());
        p.setStockQuantity(dto.getStockQty());

        CatalogueProduct saved = catalogueProductRepository.save(p);

        // Map back to the response you already use in search results
        ProductSearchRespDto resp = getProductSearchRespDto(saved);
        return resp;
    }

    private static ProductSearchRespDto getProductSearchRespDto(CatalogueProduct saved) {
        ProductSearchRespDto resp = new ProductSearchRespDto();
        resp.setProductId(saved.getId());
        resp.setCode(saved.getCode());
        resp.setDescription(saved.getDescription());
        resp.setShortDescription(saved.getShortDescription());
        resp.setPrice(saved.getPrice());
        resp.setMinimumQty(saved.getMinimumQty());
        resp.setPackageSize(saved.getPackageSize());
        resp.setPackageWeight(saved.getPackageWeight());
        resp.setStockQty(saved.getStockQuantity());
        return resp;
    }

}
