package al.polis.zahire.dto;

import lombok.Data;

@Data
public class ProductSearchRespDto extends InsertProductDto {

    public ProductSearchRespDto (String code, String descr, Double price){
        setCode(code);
        setDescription(descr);
        setPrice(price);
    }


}
