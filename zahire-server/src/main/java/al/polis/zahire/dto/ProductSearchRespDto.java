package al.polis.zahire.dto;

import lombok.Data;

@Data
public class ProductSearchRespDto extends InsertProductDto {

    public ProductSearchRespDto (String code, String descr, String shortDescr,
                                 Double price){
        setCode(code);
        setDescription(descr);
        setShortDescription(shortDescr);
        setPrice(price);
    }


}
