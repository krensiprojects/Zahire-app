package al.polis.zahire.dto;

import lombok.Data;

@Data
public class ProductSearchRespDto extends InsertProductDto {

    private Long productId;

    public ProductSearchRespDto (Long productId,String code, String descr, String shortDescr,
                                 Double price){
        setProductId(productId);
        setCode(code);
        setDescription(descr);
        setShortDescription(shortDescr);
        setPrice(price);
    }


}
