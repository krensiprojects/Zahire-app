package al.polis.zahire.controller;


import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.dto.ProductSearchReqDto;
import al.polis.zahire.dto.ProductSearchRespDto;
import al.polis.zahire.dto.RemoveProductDto;
import al.polis.zahire.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    // Endpoint to search in the catalogue
    @PostMapping("/searchInCatalogue")
    public List<ProductSearchRespDto> searchInCatalogue(@RequestBody ProductSearchReqDto searchCriterion) {
        return catalogueService.searchInCatalogue(searchCriterion);
    }
    // Endpoint to insert a new product

    @PostMapping("/insertProduct")
    public void insertProduct(@RequestBody InsertProductDto product) {

        catalogueService.insertNewProduct(product);

    }

    // Endpoint to search in the catalogue
    @PostMapping("/removeProduct")
    public  List<ProductSearchRespDto> removeProduct(@RequestBody RemoveProductDto dto) {
        return catalogueService.removeProduct(dto.getId());
    }
}
