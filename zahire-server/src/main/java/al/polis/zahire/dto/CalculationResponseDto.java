package al.polis.zahire.dto;

import lombok.Data;

@Data
public class CalculationResponseDto {
    private double sum;
    private double product;
    private double division;

    //Constructor to set the result
    public CalculationResponseDto (double sum, double product, double division){
        this.sum=sum;
        this.product=product;
        this.division=division;
    }

}
