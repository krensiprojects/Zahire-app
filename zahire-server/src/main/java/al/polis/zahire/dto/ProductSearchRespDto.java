package al.polis.zahire.dto;

public class ProductSearchRespDto extends InsertProductDto {

    public ProductSearchRespDto (String code, String descr, Double price){
        setCode(code);
        setDescription(descr);
        setPrice(price);
    }


}
