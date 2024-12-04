package al.polis.zahire.mapper;

import al.polis.zahire.dto.InsertProductDto;
import al.polis.zahire.model.CatalogueProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatalogProductMapper {
    CatalogProductMapper INSTANCE = Mappers.getMapper(CatalogProductMapper.class);

    CatalogueProduct fromInsertProductDto(InsertProductDto dto);

    InsertProductDto fromCatalogueProduct(CatalogueProduct product);
}
