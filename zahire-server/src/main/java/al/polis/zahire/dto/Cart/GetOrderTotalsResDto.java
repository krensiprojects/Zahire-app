package al.polis.zahire.dto.Cart;

import lombok.Data;

@Data
public class GetOrderTotalsResDto {
    private Double subtotal;       // Sum of all item prices
    private Double shippingFee;    // Shipping cost
    private Double taxAmount;      // Total tax calculated
    private Double totalDiscount;  // Discount applied
    private Double total;          // Grand total
    private String currency;       // Currency (e.g., "USD", "EUR")

}
