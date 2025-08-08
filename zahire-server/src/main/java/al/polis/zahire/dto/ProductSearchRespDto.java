package al.polis.zahire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRespDto extends InsertProductDto {

    private Long productId;

    public ProductSearchRespDto (Long productId,String code, String descr, String shortDescr,
                                 double price){
        setProductId(productId);
        setCode(code);
        setDescription(descr);
        setShortDescription(shortDescr);
        setPrice(price);
    }


}
