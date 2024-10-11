package al.polis.zahire.dto;

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

    //Getters for each result
    public double getSum(){
        return sum;
    }

    public double getProduct() {
        return product;
    }

    public double getDivision() {
        return division;
    }
}
