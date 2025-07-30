package al.polis.zahire.dto;

import lombok.Data;

@Data
public class InsertProductDto {
    private String code;
    private String description;
    private String shortDescription;
    private double price;
    private Integer minimumQty;
    private Integer packageSize;
    private double packageWeight;
    private double stockQty;


}
